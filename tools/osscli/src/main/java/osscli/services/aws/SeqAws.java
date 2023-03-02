package osscli.services.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import osscli.exception.LaunderOssExceptions;
import osscli.exception.OssBaseException;
import osscli.services.AbstractOss;
import osscli.services.model.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static osscli.services.model.transform.RequestTransformers.*;
import static osscli.services.model.transform.ResponseTransformers.*;

/**
 * @author : anger
 * Oss 服务巨杉 AWS 协议实现
 */
public class SeqAws extends AbstractOss<AmazonS3> {

    public SeqAws(OssConfiguration configuration) {
        super(configuration);
    }

    @Override
    public ListBucketsResponse listBuckets(ListBucketsRequest request) {
        List<com.amazonaws.services.s3.model.Bucket> buckets =
            client.listBuckets(seqAwsListBucketRequestTransformer.transform(request));
        return seqAwsListBucketsResponseTransformer.transform(buckets);
    }

    @Override
    public ListBucketsResponse listBuckets() {
        return listBuckets(new ListBucketsRequest());
    }

    @Override
    public PutBucketResponse createBucket(PutBucketRequest request) {
        com.amazonaws.services.s3.model.Bucket bucket =
                client.createBucket(seqAwsCreateBucketRequestTransformer.transform(request));
        return seqAwsCreateBucketResponseTransformer.transform(bucket);
    }

    @Override
    public PutBucketResponse createBucket(String bucketName) {
        return createBucket(new PutBucketRequest(bucketName));
    }

    @Override
    public PutObjectResponse putObject(PutObjectRequest request) {
        com.amazonaws.services.s3.model.PutObjectRequest putObjectRequest =
            seqAwsPutObjectRequestTransformer.transform(request);
        PutObjectResult result = client.putObject(putObjectRequest);

        PutObjectResponse response =
            seqAwsPutObjectResponseTransformer.transform(result);
        response.setKey(request.getKey());
        return response;
    }

    @Override
    public PutObjectResponse putObject(String bucket, File file) {
        return putObject(new PutObjectRequest(bucket, file.getName(), file));
    }

    @Override
    @SuppressWarnings("unchecked")
    public OssObject<S3Object> getObject(GetObjectRequest request) {
        com.amazonaws.services.s3.model.GetObjectRequest getObjectRequest =
            seqAwsGetObjectRequestTransformer.transform(request);
        S3Object object = client.getObject(getObjectRequest);
        OssObject<S3Object> ossObject = new OssObject<>();
        ossObject.setObject(object);
        return ossObject;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OssObject<S3Object> getObject(String bucket, String key) {
        return getObject(new GetObjectRequest(bucket, key));
    }

    @Override
    public DownloadObjectResponse downloadObject(DownloadObjectRequest request) {

        S3Object result = client.getObject(request.getBucket(), request.getKey());

        File localFile = Paths.get(request.getDownloadPath(), request.getKey()).toFile();

        if (!localFile.getParentFile().exists()) {
            try {
                Files.createDirectories(localFile.getParentFile().toPath());
            } catch (IOException e) {
                throw new OssBaseException(e);
            }
        }

        long size = 0;
        try (BufferedInputStream s3is = new BufferedInputStream(result.getObjectContent(), BUFFER_SIZE);
                BufferedOutputStream fos = new BufferedOutputStream(
                    Files.newOutputStream(Paths.get(request.getDownloadPath(), request.getKey())), BUFFER_SIZE)) {
            int len;
            byte[] readBuf = new byte[BUFFER_SIZE];
            while ((len = s3is.read(readBuf)) != -1) {
                fos.write(readBuf, 0, len);
                size += len;
            }
        } catch (Exception e) {
            LaunderOssExceptions.launder(e);
        }

        return new DownloadObjectResponse(request.getBucket(),
                                          request.getKey(),
                                          localFile.getAbsolutePath(),
                                          size);
    }

    @Override
    public DownloadObjectResponse downloadObject(String bucket, String key, String path) {
        return downloadObject(new DownloadObjectRequest(bucket, key, path));
    }

    @Override
    public DeleteObjectResponse deleteObject(DeleteObjectRequest request) {
        client.deleteObject(seqAwsObjectDeleteRequestTransformer.transform(request));
        return new DeleteObjectResponse(request.getBucket(), request.getKey());
    }

    @Override
    public DeleteObjectResponse deleteObject(String bucket, String key) {
        return deleteObject(new DeleteObjectRequest(bucket, key));
    }

    @Override
    public ListObjectsResponse listObjects(ListObjectsRequest request) {
        ListObjectsV2Result result =
            client.listObjectsV2(seqAwsListObjectRequestTransformer.transform(request));
        return seqAwsListObjectResponseTransformer.transform(result);
    }

    @Override
    public ListObjectsResponse listObjects(String bucket, String prefix) {
        return listObjects(new ListObjectsRequest(bucket, prefix));
    }

    @Override
    public ListAllObjectsResponse listAllObjects(ListAllObjectRequest request) {
        List<ObjectSummary> objectSummaryList = new ArrayList<>();

        ListObjectsRequest listObjectsRequest =
            new ListObjectsRequest(request.getBucket(), request.getPrefix());
        Supplier<List<ObjectSummary>> supplier = () ->
            listObjects(listObjectsRequest).getObjectSummaries();

        List<ObjectSummary> summaries;

        while (objectSummaryList.addAll(summaries = supplier.get()))
            listObjectsRequest
                .setStartAfter(summaries.get(summaries.size() - 1).getKey());

        ListAllObjectsResponse response = new ListAllObjectsResponse();
        response.setObjectSummaryList(objectSummaryList);

        return response;
    }

    @Override
    public ListAllObjectsResponse listAllObjects(String bucket, String prefix) {
        return listAllObjects(new ListAllObjectRequest(bucket, prefix));
    }

    @Override
    public ListAllObjectsResponse listAllObjects(String bucket) {
        return listAllObjects(new ListAllObjectRequest(bucket));
    }

    @Override
    public BatchOperationResponse batchUpload(BatchUploadRequest request) {
        final List<PutObjectRequest> requests;
        try (Stream<Path> pathStream = Files.walk(Paths.get(request.getLocalPath())) ) {
            requests = pathStream
                        .map(Path::toFile)
                        .filter(File::isFile)
                        .map(file ->
                            new PutObjectRequest(request.getBucket(),
                                file.getPath().replace(request.getLocalPath(), ""), file))
                        .collect(Collectors.toList());
        } catch (IOException e) {
            throw new OssBaseException(e);
        }

        BatchOperationResponse response = new BatchUploadResponse();

        return batchProcess(response,
                            requests,
                            PutObjectRequest::getKey,
                            putObjectRequest -> () -> putObject(putObjectRequest));
    }

    @Override
    public BatchOperationResponse batchUpload(String bucket, String path) {
        return batchUpload(new BatchUploadRequest(bucket, path));
    }

    @Override
    public BatchOperationResponse batchDownload(BatchDownloadRequest request) {
        BatchOperationResponse response = new BatchDownloadResponse();

        return batchProcess(response,
                            listAllObjects(request.getBucket()).getObjectSummaryList(),
                            ObjectSummary::getKey,
                            objectSummary -> () -> downloadObject(objectSummary.getBucket(),
                                                                  objectSummary.getKey(),
                                                                  request.getDownloadPath()));
    }

    @Override
    public BatchOperationResponse batchDownload(String bucket, String path) {
        return batchDownload(new BatchDownloadRequest(bucket, path));
    }

    @Override
    public BatchOperationResponse batchDelete(BatchDeleteRequest request) {
        BatchOperationResponse response = new BatchDeleteResponse();

        return batchProcess(response,
                            listAllObjects(request.getBucket(), request.getPrefix()).getObjectSummaryList(),
                            ObjectSummary::getKey,
                            objectSummary -> () -> deleteObject(objectSummary.getBucket(),
                                                                objectSummary.getKey()));
    }

    @Override
    public BatchOperationResponse batchDelete(String bucket) {
        return batchDelete(new BatchDeleteRequest(bucket));
    }

    @Override
    public AmazonS3 createClient(OssConfiguration configuration) {
        return new AmazonS3Initializer(configuration).s3;
    }

    private static final class AmazonS3Initializer {
        private final AmazonS3 s3;
        private final OssConfiguration configuration;

        AmazonS3Initializer(OssConfiguration configuration) {
            this.configuration = configuration;
            s3 = initialize();
        }

        private AmazonS3 initialize() {
            String endPoint = configuration.getEndPoint();
            String accessKey = configuration.getAccessKey();
            String secreteKey = configuration.getSecreteKey();

            AWSCredentials credentials = new BasicAWSCredentials(accessKey, secreteKey);
            AWSStaticCredentialsProvider provider = new AWSStaticCredentialsProvider(credentials);
            AwsClientBuilder.EndpointConfiguration endpointConfiguration =
                new AwsClientBuilder.EndpointConfiguration(endPoint, null);

            return AmazonS3ClientBuilder.standard()
                                        .withEndpointConfiguration(endpointConfiguration)
                                        .withPathStyleAccessEnabled(true)
                                        .withCredentials(provider)
                                        .build();
        }
    }
}
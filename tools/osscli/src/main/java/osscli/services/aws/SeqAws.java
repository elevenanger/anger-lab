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
import osscli.exception.LaunderAwsExceptions;
import osscli.services.AbstractOss;
import osscli.services.model.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;
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
    public OssObject<S3Object> getObject(GetObjectRequest request) {
        com.amazonaws.services.s3.model.GetObjectRequest getObjectRequest =
            seqAwsGetObjectRequestTransformer.transform(request);
        S3Object object = client.getObject(getObjectRequest);
        OssObject<S3Object> ossObject = new OssObject<>();
        ossObject.setObject(object);
        return ossObject;
    }

    @Override
    public DownloadObjectResponse downloadObject(DownloadObjectRequest request) {

        S3Object result = client.getObject(request.getBucket(), request.getKey());
        File localFile = Paths.get(request.getDownloadPath(), request.getKey()).toFile();

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
            LaunderAwsExceptions.launder(e);
        }

        return new DownloadObjectResponse(request.getBucket(), request.getKey(), localFile.getAbsolutePath(), size);
    }

    @Override
    public DownloadObjectResponse downloadObject(String bucket, String key, String path) {
        return downloadObject(new DownloadObjectRequest(bucket, key, path));
    }

    @Override
    public OssObject<S3Object> getObject(String bucket, String key) {
        return getObject(new GetObjectRequest(bucket, key));
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
        Map<String, CompletableFuture<PutObjectResponse>> futuresMap =
            Arrays.stream(Objects.requireNonNull(new File(request.getLocalPath()).listFiles()))
                .filter(File::isFile)
                .map(file -> new PutObjectRequest(request.getBucket(), file.getName(), file))
                .collect(Collectors.toMap(
                    PutObjectRequest::getKey,
                    putObjectRequest ->
                        supplyAsync(() ->
                            putObject(putObjectRequest))));

        final BatchOperationResponse response = new BatchUploadResponse();

        futuresMap.forEach((key, future) -> {
            try {
                future.get();
                response.addSuccessResult(key);
            } catch (ExecutionException e) {
                response.addErrorResult(key, e.getMessage());
                LaunderAwsExceptions.launder(e);
            } catch (InterruptedException e) {
                response.addErrorResult(key, e.getMessage());
                Thread.currentThread().interrupt();
            }
        });

        return response;
    }

    @Override
    public BatchOperationResponse batchUpload(String bucket, String path) {
        return batchUpload(new BatchUploadRequest(bucket, path));
    }

    @Override
    public BatchOperationResponse batchDownload(BatchDownloadRequest request) {
        Map<String, CompletableFuture<DownloadObjectResponse>> futureMap =
            listAllObjects(request.getBucket()).getObjectSummaryList().stream()
                .collect(Collectors.toMap(
                    ObjectSummary::getKey,
                    objectSummary ->
                        supplyAsync(() ->
                            downloadObject(
                                objectSummary.getBucket(),
                                objectSummary.getKey(),
                                request.getDownloadPath()))));

        BatchOperationResponse response = new BatchDownloadResponse();

        futureMap.forEach((key, future) -> {
            try {
                future.get();
                response.addSuccessResult(key);
            } catch (ExecutionException e) {
                response.addErrorResult(key, e.getMessage());
            } catch (InterruptedException e) {
                response.addErrorResult(key, e.getMessage());
                Thread.currentThread().interrupt();
            }
        });

        return response;
    }

    @Override
    public BatchOperationResponse batchDownload(String bucket, String path) {
        return batchDownload(new BatchDownloadRequest(bucket, path));
    }

    @Override
    public AmazonS3 createClient(OssConfiguration configuration) {
        return new AmazonS3Initializer(configuration).s3;
    }

    private static final class AmazonS3Initializer {
        final AmazonS3 s3;
        final OssConfiguration configuration;

        AmazonS3Initializer(OssConfiguration configuration) {
            this.configuration = configuration;
            s3 = initialize();
        }

        AmazonS3 initialize() {
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
package osscli.services.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import osscli.exception.OssBaseException;
import osscli.services.AbstractOss;
import osscli.services.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static osscli.services.model.transform.RequestTransformers.awsDeleteBucketRequestTransformer;
import static osscli.services.model.transform.RequestTransformers.seqAwsObjectDeleteRequestTransformer;

/**
 * @author : anger
 * Oss 服务巨杉 AWS 协议实现
 */
public class SeqAws extends AbstractOss<AmazonS3> {

    public SeqAws(OssConfiguration configuration) {
        super(configuration);
    }

    @Override
    public PutBucketResponse createBucket(PutBucketRequest request) {
        return execute(request);
    }

    @Override
    public PutBucketResponse createBucket(String bucketName) {
        return createBucket(new PutBucketRequest(bucketName));
    }

    @Override
    public DeleteBucketResponse deleteBucket(DeleteBucketRequest request) {
        client.deleteBucket(awsDeleteBucketRequestTransformer.transform(request));
        return new DeleteBucketResponse(request.getBucket());
    }

    @Override
    public DeleteBucketResponse deleteBucket(String bucket) {
        return deleteBucket(new DeleteBucketRequest(bucket));
    }

    @Override
    public PutObjectResponse putObject(PutObjectRequest request) {
        PutObjectResponse response = execute(request);
        response.setKey(request.getKey());
        return response;
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
        request.setMaxKeys(MAX_KEYS);
        return execute(request);
    }

    @Override
    public ListAllObjectsResponse listAllObjects(ListAllObjectRequest request) {
        List<ObjectSummary> objectSummaryList = new ArrayList<>();

        ListObjectsRequest listObjectsRequest =
            new ListObjectsRequest(request.getBucket(), request.getPrefix());

        while (objectSummaryList.addAll(listObjects(listObjectsRequest).getObjectSummaries()))
                listObjectsRequest
                    .setStartAfter(objectSummaryList.get(objectSummaryList.size() - 1).getKey());

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
        try (Stream<Path> pathStream = Files.walk(Paths.get(request.getLocalPath()))) {
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
    public BatchOperationResponse batchDownload(String bucket, String path, String prefix) {
        return batchDownload(new BatchDownloadRequest(bucket, path, prefix));
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
    public BatchOperationResponse batchDelete(String bucket, String prefix) {
        return batchDelete(new BatchDeleteRequest(bucket, prefix));
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
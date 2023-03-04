package osscli.services.model.transform;

import com.amazonaws.services.s3.model.*;
import osscli.services.model.*;
import osscli.services.model.DeleteBucketRequest;
import osscli.services.model.DeleteObjectRequest;
import osscli.services.model.GetObjectRequest;
import osscli.services.model.ListBucketsRequest;
import osscli.services.model.ListObjectsRequest;
import osscli.services.model.PutObjectRequest;

/**
 * @author : anger
 * 请求转换器
 */
public class RequestTransformers {

    private RequestTransformers() {}

    public static final RequestTransformer<ListBucketsRequest, com.amazonaws.services.s3.model.ListBucketsRequest>
        seqAwsListBucketRequestTransformer =
            request -> new com.amazonaws.services.s3.model.ListBucketsRequest();

    public static final RequestTransformer<PutBucketRequest, CreateBucketRequest> seqAwsCreateBucketRequestTransformer =
            request -> new CreateBucketRequest(request.getBucketName());

    /**
     * 巨杉 AWS list Object 请求转换器
     */
    public static final RequestTransformer<ListObjectsRequest, ListObjectsV2Request> seqAwsListObjectRequestTransformer =
        originRequest -> {
            ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request();
            listObjectsV2Request.setBucketName(originRequest.getBucketName());
            listObjectsV2Request.setPrefix(originRequest.getPrefix());
            listObjectsV2Request.setStartAfter(originRequest.getStartAfter());
            listObjectsV2Request.setMaxKeys(originRequest.getMaxKeys());
            return listObjectsV2Request;
        };

    public static final RequestTransformer<PutObjectRequest, com.amazonaws.services.s3.model.PutObjectRequest>
        seqAwsPutObjectRequestTransformer =
        originRequest -> {
            if (originRequest.getInputStream() == null) {
                return new com.amazonaws.services.s3.model.PutObjectRequest(
                            originRequest.getBucketName(),
                            originRequest.getKey(),
                            originRequest.getFile());
            } else {
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(originRequest.getFile().length());
                return new com.amazonaws.services.s3.model.PutObjectRequest(
                            originRequest.getBucketName(),
                            originRequest.getKey(),
                            originRequest.getInputStream(),
                            objectMetadata
                );
            }
        };

    public static final RequestTransformer<GetObjectRequest, com.amazonaws.services.s3.model.GetObjectRequest>
        seqAwsGetObjectRequestTransformer =
        originRequest ->
            originRequest.getVersionId() == null ?
                new com.amazonaws.services.s3.model.GetObjectRequest(
                    originRequest.getBucketName(),
                    originRequest.getKey()) :
                new com.amazonaws.services.s3.model.GetObjectRequest(
                    originRequest.getBucketName(),
                    originRequest.getKey(),
                    originRequest.getVersionId());

    public static final RequestTransformer<DeleteObjectRequest, com.amazonaws.services.s3.model.DeleteObjectRequest>
        seqAwsObjectDeleteRequestTransformer =
        originRequest ->
            new com.amazonaws.services.s3.model.DeleteObjectRequest(
                originRequest.getBucket(),
                originRequest.getKey());

    public static final RequestTransformer<DeleteBucketRequest, com.amazonaws.services.s3.model.DeleteBucketRequest>
        awsDeleteBucketRequestTransformer =
        deleteBucketRequest ->
            new com.amazonaws.services.s3.model.DeleteBucketRequest(deleteBucketRequest.getBucket());
}
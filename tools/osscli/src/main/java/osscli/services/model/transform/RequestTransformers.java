package osscli.services.model.transform;

import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import osscli.services.model.*;

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
        originRequest -> new com.amazonaws.services.s3.model.PutObjectRequest(
            originRequest.getBucketName(),
            originRequest.getKey(),
            originRequest.getFile()
        );

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
}
package osscli.services.model.transform;

import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ObjectMetadata;
import osscli.exception.OssBaseException;
import osscli.services.model.*;

import java.lang.reflect.Field;
import java.util.Optional;

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

    public static class TransCreateBucket implements RequestTransformer<PutBucketRequest, CreateBucketRequest> {
        @Override
        public CreateBucketRequest transform(PutBucketRequest putBucketRequest) {
            return new CreateBucketRequest(putBucketRequest.getBucketName());
        }
    }

    public static <T extends CliRequest, R> R doTransform(T t) {
        Optional<R> request = Optional.empty();
        try {
            Field[] fields = RequestTransformers.class.getFields();
            for (Field field : fields) {
                if (field.getGenericType().getTypeName().contains(t.getClass().getTypeName())) {
                    @SuppressWarnings("unchecked")
                    RequestTransformer<T, R> requestTransformer = (RequestTransformer<T, R>) field.get(RequestTransformers.class);
                    request = Optional.ofNullable(requestTransformer.transform(t));
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            throw new OssBaseException(e);
        }
        return request.orElseThrow(() -> new OssBaseException("无法转换的请求 : " + t.getClass()));
    }

}
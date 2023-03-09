package osscli.services.model.transform;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import osscli.exception.OssBaseException;
import osscli.services.Oss;
import osscli.services.model.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : anger
 * response 转换器
 */
public class ResponseTransformers {
    private ResponseTransformers() {}

    public static final ResponseTransformer<List<Bucket>, ListBucketsResponse>
        seqAwsListBucketsResponseTransformer =
            buckets -> {
                List<osscli.services.model.Bucket> bs =
                    buckets.stream()
                        .map(bucket -> {
                            osscli.services.model.Bucket b = new osscli.services.model.Bucket();
                            b.setType(Oss.Type.AWS);
                            b.setName(bucket.getName());
                            b.setCreateDate(bucket.getCreationDate());
                            return b;})
                        .collect(Collectors.toList());
                return new ListBucketsResponse(bs);
            };

    /**
     * 创建桶请求转换器
     */
    public static final ResponseTransformer<Bucket, PutBucketResponse> seqAwsCreateBucketResponseTransformer =
            bucket -> {
                osscli.services.model.Bucket b = new osscli.services.model.Bucket();
                b.setName(bucket.getName());
                b.setCreateDate(bucket.getCreationDate());
                return new PutBucketResponse(b);
            };
    public static final ResponseTransformer<ListObjectsV2Result, ListObjectsResponse> seqAwsListObjectResponseTransformer =
        listObjectsV2Result -> {
            ListObjectsResponse response = new ListObjectsResponse();

            List<ObjectSummary> objectSummaries =
                listObjectsV2Result.getObjectSummaries().stream()
                    .map(objectSummary -> {
                        ObjectSummary summary = new ObjectSummary();
                        summary.setBucket(objectSummary.getBucketName());
                        summary.setETag(objectSummary.getETag());
                        summary.setKey(objectSummary.getKey());
                        summary.setSize(objectSummary.getSize());
                        summary.setLastModified(objectSummary.getLastModified());
                        return summary;
                    }).collect(Collectors.toList());

            response.setObjectSummaries(objectSummaries);
            response.setCount(listObjectsV2Result.getKeyCount());
            response.setStartAfter(listObjectsV2Result.getStartAfter());
            response.setMaxKey(listObjectsV2Result.getMaxKeys());
            response.setCount(objectSummaries.size());

            return response;
        };

    public static final ResponseTransformer<PutObjectResult, PutObjectResponse> seqAwsPutObjectResponseTransformer =
        putObjectResult -> {
            PutObjectResponse response = new PutObjectResponse();
            response.setETag(putObjectResult.getETag());
            return response;
        };

    public static <T, R extends CliResponse> R doTransform(T t) {
        Optional<R> response = Optional.empty();
        try {
            Field[] fields = ResponseTransformers.class.getFields();
            for (Field field : fields) {
                if (field.getGenericType().getTypeName().contains(t.getClass().getTypeName())) {
                    @SuppressWarnings("unchecked")
                    ResponseTransformer<T, R> responseTransformer =
                        (ResponseTransformer<T, R>) field.get(ResponseTransformer.class);
                    response = Optional.ofNullable(responseTransformer.transform(t));
                    break;
                }
            }
        } catch (IllegalAccessException e) {
            throw new OssBaseException(e);
        }
        return response.orElseThrow(() -> new OssBaseException("无法转换的响应 : " + t.getClass()));
    }

}

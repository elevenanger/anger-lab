package osscli.services.model.transform;

import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import osscli.services.model.ListObjectsResponse;
import osscli.services.model.ObjectSummary;
import osscli.services.model.PutObjectResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : anger
 * response 转换器
 */
public class ResponseTransformers {
    private ResponseTransformers() {}

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

}

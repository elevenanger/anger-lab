package osscli.services.model.transform;

import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import osscli.services.model.ListObjectResponse;
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

    public static final ResponseTransformer<ListObjectsV2Result, ListObjectResponse> seqAwsListObjectResponseTransformer =
        listObjectsV2Result -> {
            ListObjectResponse response = new ListObjectResponse();

            List<ObjectSummary> objectSummaries =
                response.getObjectSummaries().stream()
                    .map(objectSummary -> {
                        ObjectSummary summary = new ObjectSummary();
                        summary.setBucket(objectSummary.getBucket());
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

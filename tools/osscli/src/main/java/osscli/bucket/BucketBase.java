package osscli.bucket;

import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osscli.client.AWSClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class BucketBase {

    @Autowired
    private AWSClient s3Client;

    public List<S3ObjectSummary> listObjects(String bucketName, String prefix) {
        List<S3ObjectSummary> summaries = new ArrayList<>();

        ListObjectsV2Request request = new ListObjectsV2Request();
        request.setMaxKeys(5);
        request.setPrefix(prefix);
        request.setBucketName(bucketName);

        while (summaries.addAll(s3Client.getS3Client().listObjectsV2(request).getObjectSummaries()))
            request.setStartAfter(summaries.get(summaries.size() - 1).getKey());

        return summaries;
    }
}

package seqaws.bucket;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seqaws.client.AWSClient;
import seqaws.exception.LaunderAwsExceptions;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class BucketBase {

    @Autowired
    private AWSClient s3Client;

    public void putBucket(String bucketName) {
        log.info("putBucket enter");
        try {
            s3Client.getS3Client().createBucket(bucketName);
        } catch (AmazonServiceException e) {
            LaunderAwsExceptions.launder(e);
        } catch (Exception e) {
            log.error("error message : {}",  e.getMessage());
        } finally {
            log.info("putBucket exit");
        }
    }

    public void deleteBucket(String bucket) {
        log.info("deleteBucket enter");
        try {
            s3Client.getS3Client().deleteBucket(bucket);
        } catch (AmazonServiceException e) {
            LaunderAwsExceptions.launder(e);
        } catch (Exception e) {
            log.error("error message : {}",  e.getMessage());
        } finally {
            log.info("deleteBucket exit");
        }
    }

    public void listBuckets() {
        log.info("listBuckets enter");
        try {
            List<Bucket> buckets = s3Client.getS3Client().listBuckets();
            log.info("bucket number:" + buckets.size());
            for (Bucket bucket : buckets) {
                log.info(bucket.getName());
            }
        } catch (AmazonServiceException e) {
            LaunderAwsExceptions.launder(e);
        } catch (Exception e) {
            log.error("error message : {}",  e.getMessage());
        } finally {
            log.info("listBuckets exit");
        }
    }

    public List<S3ObjectSummary> listObjects(String bucketName, String prefix) {
        log.info("listObjects enter");
        List<S3ObjectSummary> summaries = new ArrayList<>();
        ListObjectsV2Request request = new ListObjectsV2Request();
        request.setMaxKeys(5);
        request.setPrefix(prefix);
        request.setBucketName(bucketName);
        try {
            while (summaries.addAll(s3Client.getS3Client().listObjectsV2(request).getObjectSummaries())) {
                request.setStartAfter(summaries.get(summaries.size() - 1).getKey());
            }
        } catch (AmazonServiceException e) {
            LaunderAwsExceptions.launder(e);
        } catch (Exception e) {
            log.error("error message : {}",  e.getMessage());
        } finally {
            log.info("listObjects exit");
        }
        return summaries;
    }
}

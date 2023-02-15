package seqaws.object;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seqaws.client.AWSClient;
import seqaws.exception.LaunderAwsExceptions;

import java.io.*;

@Component
@Slf4j
public class ObjectBase {

    @Autowired
    private AWSClient s3Client;

    public void putObjectWithContent(String bucketName, String objectName, String content) {
        log.info("putObject enter");
        try {
            s3Client.getS3Client().putObject(bucketName, objectName, content);
        } catch (AmazonServiceException e) {
            LaunderAwsExceptions.launder(e);
        } catch (Exception e) {
            log.error("error message : {}",  e.getMessage());
        } finally {
            log.info("putObject exit");
        }
    }

    public void putObjectWithFile(String bucketName, String objectName, File file) throws IOException {
        log.info("putObjectWithFile enter");
        try {
            PutObjectRequest request = new PutObjectRequest(bucketName, objectName, file);
            s3Client.getS3Client().putObject(request);
        } catch (AmazonServiceException e) {
            LaunderAwsExceptions.launder(e);
        } catch (Exception e) {
            log.error("error message : {}",  e.getMessage());
        } finally {
            log.info("putObjectWithFile exit");
        }
    }

    public void deleteObject(String bucketName, String objectName) {
        log.info("deleteObject enter");
        try {
            s3Client.getS3Client().deleteObject(bucketName, objectName);
        } catch (AmazonServiceException e) {
            LaunderAwsExceptions.launder(e);
        } catch (Exception e) {
            log.error("error message : {}",  e.getMessage());
        } finally {
            log.info("deleteObject exit");
        }
    }

    public void getObject(String bucketName, String objectName) {
        log.info("getObject enter");
        GetObjectRequest request = new GetObjectRequest(bucketName, objectName, null);
        S3Object result = s3Client.getS3Client().getObject(request);
        
        try (S3ObjectInputStream s3is = result.getObjectContent();
             FileOutputStream fos = new FileOutputStream(objectName)) {
            byte[] read_buf = new byte[1024 * 1024];
            int read_len;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
        } catch (AmazonServiceException e) {
            LaunderAwsExceptions.launder(e);
        } catch (Exception e) {
            log.error("error message : {}",  e.getMessage());
        }
    }
}

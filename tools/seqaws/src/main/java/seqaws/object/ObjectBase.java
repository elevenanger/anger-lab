package seqaws.object;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seqaws.client.AWSClient;
import seqaws.exception.LaunderAwsExceptions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ObjectBase {
    
    private static final int BUFFER_SIZE = 128 * 1024;

    @Autowired
    private AWSClient s3Client;

    public void putObjectWithFile(String bucketName, String objectName, File file) {
        PutObjectRequest request = new PutObjectRequest(bucketName, objectName, file);
        s3Client.getS3Client().putObject(request);
    }

    public void getObject(String bucketName, String objectName) {
        GetObjectRequest request = new GetObjectRequest(bucketName, objectName, null);
        S3Object result = s3Client.getS3Client().getObject(request);
        
        try (S3ObjectInputStream s3is = result.getObjectContent();
             FileOutputStream fos = new FileOutputStream(objectName)) {
            byte[] readBuf = new byte[BUFFER_SIZE];
            int readLen;
            while ((readLen = s3is.read(readBuf)) > 0)
                fos.write(readBuf, 0, readLen);
        } catch (Exception e) {
            LaunderAwsExceptions.launder(e);
        }
    }

    public void getObjectWithBuffer(String bucketName, String objectName, String dir) {
        GetObjectRequest request = new GetObjectRequest(bucketName, objectName, null);
        S3Object result = s3Client.getS3Client().getObject(request);

        try (BufferedInputStream s3is = new BufferedInputStream(result.getObjectContent(), BUFFER_SIZE);
             BufferedOutputStream fos = new BufferedOutputStream(
                 Files.newOutputStream(Paths.get(dir, objectName)), BUFFER_SIZE)) {
            byte[] readBuf = new byte[BUFFER_SIZE];
            while (s3is.read(readBuf) > 0)
                fos.write(readBuf);
        } catch (Exception e) {
            LaunderAwsExceptions.launder(e);
        }
    }

    public void getObjectWithNioBuffer(String bucketName, String objectName, String dir) {
        GetObjectRequest request = new GetObjectRequest(bucketName, objectName, null);
        S3Object result = s3Client.getS3Client().getObject(request);

        try (BufferedInputStream s3is = new BufferedInputStream(result.getObjectContent(), BUFFER_SIZE);
             FileOutputStream fos = new FileOutputStream(Paths.get(dir, objectName).toFile())) {
            FileChannel fc = fos.getChannel();
            byte[] readBuf = new byte[BUFFER_SIZE];
            ByteBuffer buffer;
            while (s3is.read(readBuf) > 0) {
                buffer = ByteBuffer.wrap(readBuf);
                fc.write(buffer);
            }
        } catch (Exception e) {
            LaunderAwsExceptions.launder(e);
        }
    }
}

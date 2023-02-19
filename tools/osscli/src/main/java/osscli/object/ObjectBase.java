package osscli.object;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Component;
import osscli.exception.LaunderAwsExceptions;
import osscli.services.ClientFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class ObjectBase {

    private static final int BUFFER_SIZE = 128 * 1024;

    public void getObjectWithBuffer(String bucketName, String objectName, String dir) {
        GetObjectRequest request = new GetObjectRequest(bucketName, objectName, null);
        S3Object result = ClientFactory.s3Client().getObject(request);

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
        S3Object result = ClientFactory.s3Client().getObject(request);

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

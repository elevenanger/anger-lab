package osscli.services.model.object;

import osscli.services.model.CliRequest;

import java.io.File;

/**
 * @author : anger
 */
public class PutObjectRequest extends CliRequest {
    private String bucketName;
    private String key;
    private File file;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

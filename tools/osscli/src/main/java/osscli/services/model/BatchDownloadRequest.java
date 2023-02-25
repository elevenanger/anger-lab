package osscli.services.model;

/**
 * @author : anger
 * 批量下载请求
 */
public class BatchDownloadRequest extends CliRequest {
    String bucket;
    String downloadPath;

    public BatchDownloadRequest(String bucket, String downloadPath) {
        this.bucket = bucket;
        this.downloadPath = downloadPath;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }
}

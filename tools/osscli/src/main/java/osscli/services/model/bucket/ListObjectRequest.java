package osscli.services.model.bucket;

import osscli.services.model.CliRequest;

/**
 * @author : anger
 * 遍历对象请求
 */
public class ListObjectRequest extends CliRequest {
    // 桶名
    private String bucketName;
    // 前缀
    private String prefix;
    // 从指定的 key 后面开始检索
    private String startAfter;
    // 最多返回的 key 的数量
    private int maxKeys;

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setStartAfter(String startAfter) {
        this.startAfter = startAfter;
    }

    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getStartAfter() {
        return startAfter;
    }

    public int getMaxKeys() {
        return maxKeys;
    }
}

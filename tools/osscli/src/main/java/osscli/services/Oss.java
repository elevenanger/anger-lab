package osscli.services;

import osscli.services.model.*;

import java.io.File;

/**
 * @author : anger
 * 定义所有 OSS 相关方法
 * 顶层抽象接口
 * 具体实现需要继承 {@link AbstractOss}
 */
public interface Oss {

    int BUFFER_SIZE = 128 * 1024;

    int KEY_SIZE = 1_000;

    /**
     * 上传对象
     */
    PutObjectResponse putObject(PutObjectRequest request);

    PutObjectResponse putObject(String bucket, File file);

    /**
     * 获取对象
     */
    OssObject<?> getObject(GetObjectRequest request);

    OssObject<?> getObject(String bucket, String key);

    /**
     * 下载对象到本地
     * @param request 对象下载请求
     * @return 下载信息
     */
    DownloadObjectResponse downloadObject(DownloadObjectRequest request);

    DownloadObjectResponse downloadObject(String bucket, String key, String downloadPath);

    /**
     * 获取 bucket 中的对象
     */
    ListObjectsResponse listObjects(ListObjectsRequest request);

    ListObjectsResponse listObjects(String bucket, String prefix);

    ListAllObjectsResponse listAllObjects(ListAllObjectRequest request);

    ListAllObjectsResponse listAllObjects(String bucket, String prefix);

    BatchUploadResponse batchUpload(BatchUploadRequest request);
    BatchUploadResponse batchUpload(String bucket, String localPath);

    enum Type {
        AWS,
    }

}

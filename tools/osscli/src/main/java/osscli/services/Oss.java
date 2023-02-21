package osscli.services;

import osscli.services.model.ListObjectRequest;
import osscli.services.model.ListObjectResponse;
import osscli.services.model.GetObjectRequest;
import osscli.services.model.OssObject;
import osscli.services.model.PutObjectRequest;
import osscli.services.model.PutObjectResponse;

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
     * 设置 endPoint 地址
     * @param endpoint endPoint 地址
     */
    void setEndpoint(String endpoint);

    /**
     * 上传对象
     */
    PutObjectResponse putObject(PutObjectRequest request);

    PutObjectResponse putObject(String bucket, String fileName, File file);

    /**
     * 获取对象
     */
    OssObject<?> getObject(GetObjectRequest request);

    OssObject<?> getObject(String bucket, String key);

    void downloadObject(String bucket, String key, String downloadPath);

    /**
     * 获取 bucket 中的对象
     */
    ListObjectResponse listObjects(ListObjectRequest request);

    ListObjectResponse listObjects(String bucket, String prefix);

    enum OSSType {
        AWS,
        OSS,
        COS;
    }

}

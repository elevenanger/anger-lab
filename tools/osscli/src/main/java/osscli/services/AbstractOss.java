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
 * OSS 抽象类，提供方法的默认实现
 */
public abstract class AbstractOss implements Oss {

    @Override
    public void setEndpoint(String endpoint) {
        throw new UnsupportedOperationException("继承 AbstractOss 实现方法");
    }

    @Override
    public PutObjectResponse putObject(PutObjectRequest request) {
        throw new UnsupportedOperationException("继承 AbstractOss 实现方法");
    }

    @Override
    public PutObjectResponse putObject(String bucket, String fileName, File file) {
        throw new UnsupportedOperationException("继承 AbstractOss 实现方法");
    }

    @Override
    public OssObject<?> getObject(GetObjectRequest request) {
        throw new UnsupportedOperationException("继承 AbstractOss 实现方法");
    }

    @Override
    public OssObject<?> getObject(String bucket, String key) {
        throw new UnsupportedOperationException("继承 AbstractOss 实现方法");
    }

    @Override
    public void downloadObject(String bucket, String key, String downloadPath) {
        throw new UnsupportedOperationException("继承 AbstractOss 实现方法");
    }

    @Override
    public ListObjectResponse listObjects(ListObjectRequest request) {
        throw new UnsupportedOperationException("继承 AbstractOss 实现方法");
    }

    @Override
    public ListObjectResponse listObjects(String bucket, String prefix) {
        throw new UnsupportedOperationException("继承 AbstractOss 实现方法");
    }
}

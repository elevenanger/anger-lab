package osscli.services;

import osscli.exception.UnsupportedOssOperationException;
import osscli.services.model.*;

import java.io.File;

/**
 * @author : anger
 * OSS 抽象类，提供方法的默认实现
 */
public abstract class AbstractOss implements Oss {

    @Override
    public void setEndpoint(String endpoint) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public PutObjectResponse putObject(PutObjectRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public PutObjectResponse putObject(String bucket, String fileName, File file) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public OssObject<?> getObject(GetObjectRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public OssObject<?> getObject(String bucket, String key) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public DownloadObjectResponse downloadObject(String bucket, String key, String downloadPath) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public DownloadObjectResponse downloadObject(DownloadObjectRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public ListObjectResponse listObjects(ListObjectRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public ListObjectResponse listObjects(String bucket, String prefix) {
        throw new UnsupportedOssOperationException();
    }
}

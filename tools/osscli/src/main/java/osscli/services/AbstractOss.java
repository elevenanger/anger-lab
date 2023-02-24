package osscli.services;

import osscli.exception.UnsupportedOssOperationException;
import osscli.services.client.Client;
import osscli.services.model.*;

import java.io.File;

/**
 * @author : anger
 * OSS 抽象类，提供方法的默认实现
 */
public abstract class AbstractOss<T> implements Oss, Client<T> {

    protected final T client;

    protected AbstractOss(ClientConfiguration configuration) {
        this.client = createClient(configuration);
    }

    @Override
    public PutObjectResponse putObject(PutObjectRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public PutObjectResponse putObject(String bucket, File file) {
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
    public ListObjectsResponse listObjects(ListObjectsRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public ListObjectsResponse listObjects(String bucket, String prefix) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public ListAllObjectsResponse listAllObjects(ListAllObjectRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public ListAllObjectsResponse listAllObjects(String bucket, String prefix) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public BatchUploadResponse batchUpload(BatchUploadRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public BatchUploadResponse batchUpload(String bucket, String localPath) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public T createClient(ClientConfiguration configuration) {
        throw new UnsupportedOssOperationException();
    }
}

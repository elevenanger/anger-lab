package osscli.services;

import osscli.exception.OssBaseException;
import osscli.exception.UnsupportedOssOperationException;
import osscli.services.model.*;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author : anger
 * OSS 抽象类，提供方法的默认实现
 */
public abstract class AbstractOss<T> implements Oss, Client<T> {

    protected final T client;

    protected AbstractOss(OssConfiguration configuration) {
        this.client = createClient(configuration);
    }

    @Override
    public ListBucketsResponse listBuckets(ListBucketsRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public ListBucketsResponse listBuckets() {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public PutBucketResponse createBucket(PutBucketRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public PutBucketResponse createBucket(String bucketName) {
        throw new UnsupportedOssOperationException();
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
    public DownloadObjectResponse downloadObject(String bucket, String key, String path) {
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
    public ListAllObjectsResponse listAllObjects(String bucket) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public BatchOperationResponse batchUpload(BatchUploadRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public BatchOperationResponse batchUpload(String bucket, String path) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public BatchOperationResponse batchDownload(BatchDownloadRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public BatchOperationResponse batchDownload(String bucket, String path) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public T createClient(OssConfiguration configuration) {
        throw new UnsupportedOssOperationException();
    }

    protected <E, V> BatchOperationResponse batchProcess(final Collection<E> collection,
                                                         final Function<E, String> eleToKeyFunc,
                                                         final Function<E, Supplier<V>> eleToValSupFunc,
                                                         final BatchOperationResponse response) {
        Map<String, CompletableFuture<V>> futureMap =
            collection.stream()
                .collect(
                    Collectors.toMap(
                        eleToKeyFunc,
                        e -> CompletableFuture.supplyAsync(eleToValSupFunc.apply(e))));

        futureMap.forEach(
            (k, future) -> {
                try {
                    future.get();
                    response.addSuccessResult(k);
                } catch (ExecutionException e) {
                    response.addErrorResult(k, e.getMessage());
                    throw new OssBaseException(e);
                } catch (InterruptedException e) {
                    response.addErrorResult(k, e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        );

        return response;
    }
}

package osscli.services;

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

import static java.util.concurrent.CompletableFuture.supplyAsync;

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

    /**
     * 批量任务执行函数
     * 将需要处理的元素集合转换为 元素类型 E 和 {@link CompletableFuture<V>} 键值对的 {@link Map} 对象
     * @param collection 需要处理的元素集合
     * @param eleToKeyFunc 将集合中的元素转换为 map 的 key 的函数
     * @param eleToValSupFunc 将集合中的元素转换为 map 中的 value {@link Supplier} 的函数
     * @param response {@link BatchOperationResponse}
     * @return 组装好返回结果的 {@link BatchOperationResponse}
     * @param <E> 集合元素类型
     * @param <V> 异步执行的结果
     */
    protected <E, V> BatchOperationResponse batchProcess(final Collection<E> collection,
                                                         final Function<E, String> eleToKeyFunc,
                                                         final Function<E, Supplier<V>> eleToValSupFunc,
                                                         final BatchOperationResponse response) {
        Map<String, CompletableFuture<V>> futureMap =
            collection.stream()
                .collect(
                    Collectors.toMap(
                        eleToKeyFunc,
                        e -> supplyAsync(eleToValSupFunc.apply(e))));

        futureMap.forEach(
            (k, future) -> {
                try {
                    future.get();
                    response.addSuccessResult(k);
                } catch (ExecutionException e) {
                    response.addErrorResult(k, e.getMessage());
                } catch (InterruptedException e) {
                    response.addErrorResult(k, e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        );

        return response;
    }
}

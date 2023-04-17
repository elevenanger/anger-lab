package osscli.services;

import osscli.exception.OssBaseException;
import osscli.exception.UnsupportedOssOperationException;
import osscli.services.model.*;
import osscli.services.model.transform.RequestTransformers;
import osscli.services.model.transform.ResponseTransformers;

import javax.annotation.Nullable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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

    private final OssConfiguration ossConfiguration;

    protected AbstractOss(OssConfiguration configuration) {
        this.ossConfiguration = configuration;
        this.client = createClient(configuration);
    }

    @Override
    public ListBucketsResponse listBuckets(ListBucketsRequest request) {
        return execute(request);
    }

    @Override
    public ListBucketsResponse listBuckets() {
        return listBuckets(new ListBucketsRequest());
    }

    @Override
    public DeleteBucketResponse deleteBucket(DeleteBucketRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public DeleteBucketResponse deleteBucket(String bucket) {
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
        PutObjectResponse response = execute(request);
        response.setKey(request.getKey());
        return response;
    }

    @Override
    public PutObjectResponse putObject(String bucket, File file) {
        return putObject(new PutObjectRequest(bucket, file.getName(), file));
    }

    @Override
    public <O> GetObjectResponse<O> getObject(GetObjectRequest request) {
        return execute(request);
    }

    @Override
    public <O> GetObjectResponse<O> getObject(String bucket, String key, String rule) {
        GetObjectRequest request = new GetObjectRequest(bucket, key);
        request.putCustomQueryParameter(rule, null);
        return getObject(request);
    }

    @Override
    public DownloadObjectResponse downloadObject(String bucket, String key, String path) {
        return downloadObject(new DownloadObjectRequest(bucket, key, path));
    }

    @Override
    public DownloadObjectResponse downloadObject(String bucket, String key, String path, String rule) {
        return downloadObject(new DownloadObjectRequest(bucket, key, path, rule));
    }

    @Override
    public DownloadObjectResponse downloadObject(DownloadObjectRequest request) {

        GetObjectResponse<?> result =
                getObject(request.getBucket(), request.getKey(), request.getRule());

        File localFile = Paths.get(request.getDownloadPath(), request.getKey().split("/")).toFile();

        if (!localFile.getParentFile().exists()) {
            try {
                Files.createDirectories(localFile.getParentFile().toPath());
            } catch (IOException e) {
                throw new OssBaseException(e);
            }
        }

        long size = 0;
        try (BufferedInputStream bis = new BufferedInputStream(result.getObjectContent(), BUFFER_SIZE);
                BufferedOutputStream fos = new BufferedOutputStream(
                    Files.newOutputStream(Paths.get(request.getDownloadPath(), request.getKey())), BUFFER_SIZE)) {
            int len;
            byte[] readBuf = new byte[BUFFER_SIZE];
            while ((len = bis.read(readBuf)) != -1) {
                fos.write(readBuf, 0, len);
                size += len;
            }
        } catch (Exception e) {
            throw new OssBaseException(e);
        }

        return new DownloadObjectResponse(request.getBucket(),
                request.getKey(),
                localFile.getAbsolutePath(),
                size);
    }

    @Override
    public DeleteObjectResponse deleteObject(DeleteObjectRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public DeleteObjectResponse deleteObject(String bucket, String key) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public ListObjectsResponse listObjects(ListObjectsRequest request) {
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
    public BatchOperationResponse batchDownload(String bucket, String path, String prefix) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public BatchOperationResponse batchDelete(BatchDeleteRequest request) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public BatchOperationResponse batchDelete(String bucket, String prefix) {
        throw new UnsupportedOssOperationException();
    }

    @Override
    public OssConfiguration getCurrentConfiguration() {
        return ossConfiguration;
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
    protected <E, V, R extends BatchOperationResponse> R batchProcess(final R response,
                                                                      final Collection<E> collection,
                                                                      final Function<E, String> eleToKeyFunc,
                                                                      final Function<E, Supplier<V>> eleToValSupFunc)
    {
        response.setBatchSize(collection.size());

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


    protected <R extends CliRequest, E extends CliResponse> E execute(R req) {
        return execute(req, null);
    }

    /**
     * 调用 oss 接口的通用处理框架
     * @param req cli 项目 oss 服务源请求，继承 {@link CliRequest}
     * @return cli 项目 oss 服务响应，继承 {@link CliResponse}
     * @param <R> 源请求类型
     * @param <I> oss client 请求类型，经过 {@link RequestTransformers} 转换后的结果
     * @param <O> 执行 oss client 响应方法后的响应类型
     * @param <E> 通过 {@link ResponseTransformers} 将响应类型转换为 cli 服务的响应类型
     */
    @SuppressWarnings("unchecked")
    protected <R extends CliRequest, I, O, E extends CliResponse> E execute(R req, Class<?> clientResponseType) {
        I request = RequestTransformers.doTransform(req, req.getClass(), this.ossConfiguration.getType());
        O response;
        Method m = Arrays.stream(client.getClass().getMethods())
                    .filter(method -> {
                        if (clientResponseType != null)
                            return method.getReturnType().equals(clientResponseType);
                        return true;})
                    .filter(method -> Arrays.stream(method.getParameterTypes())
                                            .anyMatch(type -> type == request.getClass()))
                    .findFirst()
                    .orElseThrow(() -> new OssBaseException("没有匹配请求类型的方法 : " + request.getClass()));
        try {
            response = (O) m.invoke(client, request);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new OssBaseException(e);
        }

        return ResponseTransformers.doTransform(response, m.getGenericReturnType());
    }
}

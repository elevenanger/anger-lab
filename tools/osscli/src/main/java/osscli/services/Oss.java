package osscli.services;

import osscli.exception.OssBaseException;
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

    ListBucketsResponse listBuckets(ListBucketsRequest request);
    ListBucketsResponse listBuckets();

    /**
     * 创建桶
     * @param request 请求实体
     * @return 创建桶结果
     */
    PutBucketResponse createBucket(PutBucketRequest request);
    PutBucketResponse createBucket(String bucketName);

    /**
     * 上传对象
     */
    PutObjectResponse putObject(PutObjectRequest request);

    PutObjectResponse putObject(String bucket, File file);

    /**
     * 获取对象
     */
    <O> OssObject<O> getObject(GetObjectRequest request);

    <O> OssObject<O> getObject(String bucket, String key);

    /**
     * 下载对象到本地
     * @param request 对象下载请求
     * @return 下载信息
     */
    DownloadObjectResponse downloadObject(DownloadObjectRequest request);

    /**
     * 下载文件
     * @param bucket 桶名
     * @param key key
     * @param path 本地路径
     * @return 下载结果
     */
    DownloadObjectResponse downloadObject(String bucket, String key, String path);

    /**
     * 删除对象
     * @param request {@link DeleteObjectRequest}
     * @return {@link DeleteObjectResponse}
     */
    DeleteObjectResponse deleteObject(DeleteObjectRequest request);
    DeleteObjectResponse deleteObject(String bucket, String key);

    /**
     * 获取 bucket 中的对象
     */
    ListObjectsResponse listObjects(ListObjectsRequest request);

    ListObjectsResponse listObjects(String bucket, String prefix);

    ListAllObjectsResponse listAllObjects(ListAllObjectRequest request);

    ListAllObjectsResponse listAllObjects(String bucket, String prefix);

    ListAllObjectsResponse listAllObjects(String bucket);

    BatchOperationResponse batchUpload(BatchUploadRequest request);

    BatchOperationResponse batchUpload(String bucket, String path);

    BatchOperationResponse batchDownload(BatchDownloadRequest request);

    BatchOperationResponse batchDownload(String bucket, String path);

    BatchOperationResponse batchDelete(BatchDeleteRequest request);
    BatchOperationResponse batchDelete(String bucket);

    enum Type {
        AWS;

        public static Type fromValue(String value) {
            if (value == null || value.isEmpty()) {
                return null;
            }

            final String upperValue = value.toUpperCase();

            for (Type type : Type.values()) {
                if (type.name().equals(upperValue))
                    return type;
            }

            throw new OssBaseException("不支持的 OSS 类型 ".concat(value));
        }

    }

}

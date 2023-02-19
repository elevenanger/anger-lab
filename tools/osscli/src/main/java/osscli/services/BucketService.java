package osscli.services;

import osscli.services.model.bucket.ListObjectRequest;
import osscli.services.model.bucket.ListObjectResponse;

/**
 * @author : anger
 * 定义了存储桶相关的方法
 */
public interface BucketService {

    ListObjectResponse listAllObjects(ListObjectRequest request);

    /**
     * 获取 bucket 中的对象
     */
    ListObjectResponse listObjects(ListObjectRequest request);
}

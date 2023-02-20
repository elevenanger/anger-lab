package osscli.services;

import osscli.services.model.object.GetObjectRequest;
import osscli.services.model.object.OssObject;
import osscli.services.model.object.PutObjectRequest;
import osscli.services.model.object.PutObjectResponse;

/**
 * @author : anger
 * 对象相关服务
 */
public interface ObjectService {
    /**
     * 上传对象
     */
    PutObjectResponse putObject(PutObjectRequest request);

    /**
     * 获取对象
     */
    OssObject<?> getObject(GetObjectRequest request);
}

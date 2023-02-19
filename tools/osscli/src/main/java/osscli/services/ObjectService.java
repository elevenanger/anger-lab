package osscli.services;

import osscli.services.model.object.PutObjectRequest;
import osscli.services.model.object.PutObjectResponse;

/**
 * @author : anger
 * 对象相关服务
 */
public interface ObjectService {
    PutObjectResponse putObject(PutObjectRequest request);
}

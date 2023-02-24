package osscli.services.client;

import osscli.services.model.ClientConfiguration;

/**
 * @author : anger
 * OSS 客户端相关方法
 */
public interface Client<T> {
    T createClient(ClientConfiguration configuration);
}

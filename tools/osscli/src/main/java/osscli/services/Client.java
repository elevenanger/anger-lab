package osscli.services;

import osscli.services.model.ClientConfiguration;

/**
 * @author : anger
 * OSS 客户端相关方法
 */
public interface Client<T> {
    T getClient();

    void clientSetUp(ClientConfiguration configuration);
}

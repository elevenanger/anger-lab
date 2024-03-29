package osscli.services.model.transform;

import osscli.services.model.CliRequest;

/**
 * @author : anger
 * 请求转换器
 */
@FunctionalInterface
public interface RequestTransformer<T extends CliRequest, R> {
    R transform(T t);

}

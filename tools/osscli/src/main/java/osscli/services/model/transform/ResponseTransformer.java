package osscli.services.model.transform;

import osscli.services.model.CliResponse;

/**
 * @author : anger
 * 返回转换器
 */
@FunctionalInterface
public interface ResponseTransformer<T, R extends CliResponse> {
    R transform(T t);
}

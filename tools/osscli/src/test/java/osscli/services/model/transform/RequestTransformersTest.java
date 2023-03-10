package osscli.services.model.transform;

import com.amazonaws.services.s3.model.CreateBucketRequest;
import org.junit.jupiter.api.Test;
import osscli.exception.OssBaseException;
import osscli.services.model.CliRequest;
import osscli.services.model.CliResponse;
import osscli.services.model.DeleteObjectRequest;
import osscli.services.model.PutBucketRequest;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : anger
 */
class RequestTransformersTest {

    RequestTransformer<?, ?> reflectionClass(Class<? extends CliRequest> t) {
        Class<?> [] classes = RequestTransformers.class.getDeclaredClasses();
        for (Class<?> aClass : classes) {
            for (Type genericInterface : aClass.getGenericInterfaces()) {
                if (genericInterface.getTypeName().contains(t.getTypeName())) {
                    try {
                        return (RequestTransformer<?, ?>) aClass.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new OssBaseException(e);
                    }
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    <T extends CliRequest, R> R doRequestTransform(T originRequest) {
        RequestTransformer<T, R> transformer =
            (RequestTransformer<T, R>) reflectionClass(originRequest.getClass());
        if (transformer == null)
            return null;
        return transformer.transform(originRequest);
    }

    ResponseTransformer<?, ?> reflectionResponseClass(Class<?> t) {
        Class<?> [] classes = ResponseTransformer.class.getDeclaredClasses();
        for (Class<?> aClass : classes) {
            for (Type genericInterface : aClass.getGenericInterfaces()) {
                if (genericInterface.getTypeName().contains(t.getTypeName())) {
                    try {
                        return (ResponseTransformer<?, ?>) aClass.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new OssBaseException(e);
                    }
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    <T, R extends CliResponse> R doResponseTransform(T originResponse) {
        ResponseTransformer<T, R> transformer =
            (ResponseTransformer<T, R>) reflectionResponseClass(originResponse.getClass());
        if (transformer == null)
            return null;
        return transformer.transform(originResponse);
    }

    <T extends CliRequest, Request, Response, R extends CliResponse> R execute(T req, Function<Request, Response> func) {
        Request request = doRequestTransform(req);
        Response response = func.apply(request);
        return doResponseTransform(response);
    }

    @Test
    void testCreateBucket() {

        CreateBucketRequest request = doRequestTransform(new PutBucketRequest("test"));

        assertNotNull(request);

        System.out.println(request.getBucketName());
    }

    @Test
    void testReflectionTrans() throws IllegalAccessException, NoSuchFieldException {
        Field field = RequestTransformers.class.getDeclaredField("seqAwsObjectDeleteRequestTransformer");
        System.out.println(field.getGenericType());
        @SuppressWarnings("unchecked")
        RequestTransformer<DeleteObjectRequest, com.amazonaws.services.s3.model.DeleteObjectRequest> value =
            (RequestTransformer<DeleteObjectRequest, com.amazonaws.services.s3.model.DeleteObjectRequest>)
                field.get(RequestTransformers.class);

        com.amazonaws.services.s3.model.DeleteObjectRequest request =
            value.transform(new DeleteObjectRequest("local", "test"));

        assertNotNull(request);
    }

}
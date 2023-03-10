package osscli.services.model.transform;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author : anger
 */
class ResponseTransformersTest {

    @Test
    void genericTypedTransfer() {
        for (Field field : ResponseTransformers.class.getFields()) {
            String typeName = field.getGenericType().getTypeName();
            typeName = typeName.replace(field.getType().getTypeName(), "");
            typeName = typeName.subSequence(1, typeName.length() - 1).toString();
        }
    }

}
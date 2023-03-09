import org.junit.jupiter.api.Test;
import osscli.services.model.transform.RequestTransformers;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author : anger
 */
class ClassReflectionTest {

    void printClass(Class<?> cl) {
        Class<?>[] c = cl.getDeclaredClasses();
        Arrays.stream(c).forEach(System.out::println);

        Field[] fields = cl.getDeclaredFields();
        Arrays.stream(fields)
            .map(field -> field.getType() + " " + field.getGenericType() +  "" + field.getName())
            .forEach(System.out::println);
    }


    @Test
    void transformersTest() {
        printClass(RequestTransformers.class);
    }

}

package cn.anger.reflection;

import jdk.internal.org.objectweb.asm.TypeReference;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class ReflectionUtilTest {

    @Test
    void parameterizedType() {
        List<String> list = new ArrayList<>();
        list.add("a");

        Type type = ReflectionUtil.parameterizedType(list.getClass());
        System.out.println(type);
    }
}
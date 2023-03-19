package cn.anger.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class TypeTokenTest {

    @Test
    void testListType() {
        assertEquals("java.util.List<java.lang.String>",
                        new TypeToken<List<String>>() {}.getType().getTypeName());
    }


}
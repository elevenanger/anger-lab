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
    void testArray() {
        System.out.println(new TypeToken<List<String>>(){}.getType());
    }


}
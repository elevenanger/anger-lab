package cn.anger.visibility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class MutableIntegerTest {

    private final MutableInteger integer = new MutableInteger();


    @Test
    void staleValueTest() {
        new Thread(() -> integer.setValue(1)).start();
        assertNotEquals(1, integer.getValue());
    }

}
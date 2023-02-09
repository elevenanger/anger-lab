package explicitconditionobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class ConditionBoundedBufferTest {

    @Test
    void testPutAndTake() throws InterruptedException {
        ConditionBoundedBuffer<Integer> buffer = new ConditionBoundedBuffer<>();
        buffer.put(100);
        assertEquals(100, buffer.take());
    }



}
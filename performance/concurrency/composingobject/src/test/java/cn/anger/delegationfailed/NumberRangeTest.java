package cn.anger.delegationfailed;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author : anger
 */
class NumberRangeTest {

    private final NumberRange range = new NumberRange();

    @Test
    void testDelegationFailed() {
        range.setUpper(10);

        Runnable setLower = () -> {range.setLower(5);};
        Runnable setUpper = () -> {range.setUpper(3);};

        ConcurrentWorkStream
            .workerMatchingTask(setLower, setUpper)
            .doWork();

        assertFalse(range.isInRange(4));
    }

}
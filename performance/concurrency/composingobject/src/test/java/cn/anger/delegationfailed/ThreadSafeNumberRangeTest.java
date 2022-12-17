package cn.anger.delegationfailed;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author : anger
 */
class ThreadSafeNumberRangeTest {
    private final ThreadSafeNumberRange range = new ThreadSafeNumberRange();
    private final Random random = new Random();

    @Test
    void testRangeValid() {
        ConcurrentWorkStream.commonWorkStreams(
            () -> range.setUpper(random.nextInt()),
            () -> range.setLower(random.nextInt()))
            .doWork();

        Assertions.assertTrue(range.isValid());
    }

}
package cn.anger.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class ConcurrentWorkStreamTest {

    @Test
    void testCommonWorkStream() {
        AtomicInteger count = new AtomicInteger(0);
        ConcurrentWorkStream.commonWorkStream(count::getAndIncrement).doWork();
        assertEquals(1_000, count.get());
    }

}
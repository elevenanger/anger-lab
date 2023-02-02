package managingstatedependence;

import cn.anger.concurrency.ConcurrentWorkStream;
import cn.anger.random.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SleepyBoundedBufferTest {

    @Test
    void testTakeAndPut() {
        SleepyBoundedBuffer<Integer> sleepyBoundedBuffer = new SleepyBoundedBuffer<>(100);
        ConcurrentWorkStream.commonWorkStreams(
            () -> {
                try {
                    sleepyBoundedBuffer.put(Random.getRandom());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },
            () -> {
                int v = 0;
                try {
                    v = sleepyBoundedBuffer.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (v == Random.getRandom())
                    System.out.println(v);
            }
        ).doWork();
    }

}
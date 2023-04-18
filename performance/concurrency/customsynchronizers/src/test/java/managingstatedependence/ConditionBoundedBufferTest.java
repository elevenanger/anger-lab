package managingstatedependence;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import cn.anger.util.random.Random;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class ConditionBoundedBufferTest {

    @Test
    void testPutAndTake() {
        ConditionBoundedBuffer<Integer> buffer = new ConditionBoundedBuffer<>(100);
        ConcurrentWorkStream.commonWorkStreams(
            () -> {
                try {
                    buffer.put(Random.getRandom());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            },
            () -> {
                try {
                    buffer.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        ).doWork();
    }

}
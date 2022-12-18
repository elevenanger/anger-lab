package cn.anger.trackvehicle.publishversion;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @author : anger
 */
class SafePointTest {
    private final Random random = new Random();
    private final SafePoint point = new SafePoint(0, 0);
    @Test
    void testSafePoint() {
        ConcurrentWorkStream.commonWorkStreams(
            // 设置值
            () -> {
                int p = random.nextInt();
                point.set(p, p);
            },
            // 检查值的一致性
            () -> {
                int[] currentPoint = point.get().clone();
                Assertions.assertEquals(currentPoint[0], currentPoint[1]);
            }).doWork();

    }

}
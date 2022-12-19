package cn.anger.synchronizationcollectionproblem;

import cn.anger.concurrency.ThreadUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Vector;

/**
 * @author : anger
 */
class UnsafeCompoundActionTest {
    final Vector<Integer> vector = new Vector(Arrays.asList(1, 2, 3, 4));

    // 其中第一个线程会抛出异常
    @Test
    void testConcurrentProblem() {
        new Thread(() -> {
            ThreadUtil.sleep(20);
            UnsafeCompoundAction.deleteLast(vector);
        }).start();
        // 抛出 ArrayIndexOutOfBoundsException.class 异常
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> UnsafeCompoundAction.getLast(vector)
        );
    }
}
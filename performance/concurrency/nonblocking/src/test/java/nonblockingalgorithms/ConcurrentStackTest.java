package nonblockingalgorithms;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import cn.anger.util.random.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class ConcurrentStackTest {

    @Test
    void testPushAndPop() {
        ConcurrentStack<Integer> stack = new ConcurrentStack<>();
        stack.push(100);
        assertEquals(100, stack.pop());
    }

    @Test
    void testContend() {
        ConcurrentStack<Integer> stack = new ConcurrentStack<>();
        ConcurrentWorkStream.commonWorkStream(() -> stack.push(Random.getRandom())).doWork();

        int i = 0;

        for (i = 0; stack.pop() != null; i++);

        assertEquals(1000, i);
    }

}
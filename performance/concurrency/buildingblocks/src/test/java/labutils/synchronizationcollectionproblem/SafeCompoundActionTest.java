package labutils.synchronizationcollectionproblem;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Vector;

/**
 * @author : anger
 */
class SafeCompoundActionTest {

    private final Vector<Integer> vector = new Vector<>(Arrays.asList(1, 2, 3, 4, 5));

    @Test
    void testConcurrentOperation() {
        ConcurrentWorkStream.workerMatchingTask(
            () -> SafeCompoundAction.getLast(vector),
            () -> SafeCompoundAction.removeLast(vector),
            () -> SafeCompoundAction.removeLast(vector)
        ).doWork();
    }
}
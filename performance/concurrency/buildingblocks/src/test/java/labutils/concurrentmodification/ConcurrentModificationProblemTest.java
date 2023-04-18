package labutils.concurrentmodification;

import cn.anger.util.concurrency.ThreadUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class ConcurrentModificationProblemTest {
    private final List<Integer> list =
        Collections.synchronizedList(new ArrayList<>());

    private final Runnable deleteElement = () -> {
        ThreadUtil.sleep(10);
        list.remove(0);
    };

    @BeforeEach
    void setUp() {
        list.addAll(Arrays.asList(1, 2, 3, 4, 5));
        new Thread(deleteElement).start();
    }

    @AfterEach
    void tearDown() {
        list.clear();
    }

    @Test
    void forEachIteration() {
        assertThrows(ConcurrentModificationException.class, () ->
            ConcurrentModificationProblem.forEachIteration(list));
    }

    @Test
    void synchronizedForEachIteration() {
        ConcurrentModificationProblem.synchronizedForEachIteration(list);
    }

    @Test
    void forEachInnerIteration() {
        ConcurrentModificationProblem.forEachInnerIteration(list);
    }
}
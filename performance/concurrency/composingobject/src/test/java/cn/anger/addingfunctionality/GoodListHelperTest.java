package cn.anger.addingfunctionality;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class GoodListHelperTest {

    private final GoodListHelper<Integer> listHelper = new GoodListHelper<>();
    private final Random random = new Random();

    @Test
    void testPutIfAbsent() {
        ConcurrentWorkStream
            .commonWorkStreams(() -> listHelper.putIfAbsent(random.nextInt(100_000)),
                () -> listHelper.list.clear())
            .doWork();

        Set<Integer> listElements = new HashSet<>(listHelper.list);

        assertEquals(listElements.size(), listHelper.list.size());
    }
}
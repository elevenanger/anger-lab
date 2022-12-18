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
class BadListHelperTest {

    private final BadListHelper<Integer> listHelper = new BadListHelper<>();
    private final Random random = new Random();

    @Test
    void testPutIfAbsent() {
        ConcurrentWorkStream
            .commonWorkStreams(() -> listHelper.putIfAbsent(random.nextInt(100)),
                () -> listHelper.list.clear())
            .doWork();

        Set<Integer> listElements = new HashSet<>(listHelper.list);
        System.out.println(listElements.size());

        assertEquals(listElements.size(), listHelper.list.size());
    }
}
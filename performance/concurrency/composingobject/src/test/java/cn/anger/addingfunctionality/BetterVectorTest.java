package cn.anger.addingfunctionality;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;

/**
 * @author : anger
 */
class BetterVectorTest {

    private final BetterVector<Integer> integers = new BetterVector<>();
    private final Random random = new Random();

    @Test
    void putIfAbsent() {
        ConcurrentWorkStream.commonWorkStream(() -> {
            integers.putIfAbsent(random.nextInt(100));
        }).doWork();
        HashSet<Integer> set = new HashSet<>(integers);
        Assertions.assertEquals(set.size(), integers.size());
    }
}
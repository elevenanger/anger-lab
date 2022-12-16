package cn.anger.instanceconfinement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
class PrivateLockCounterTest {

    private PrivateLockCounter counter = new PrivateLockCounter();

    private final static int loop = 1000;

    @Test
    void privateLockCountTest() {
        List<Thread> threads = Stream.generate(() -> new Thread(() -> {
                for (int i = 0; i < loop; i++) {
                    counter.takeCount();
                }
            }))
            .limit(10)
            .collect(Collectors.toList());

        threads.forEach(Thread::start);

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Assertions.assertEquals(counter.takeCount(), 10 * loop);
    }
}
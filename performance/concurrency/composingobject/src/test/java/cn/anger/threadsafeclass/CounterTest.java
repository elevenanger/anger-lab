package cn.anger.threadsafeclass;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
class CounterTest {
    private final Counter counter = new Counter();
    private static final int T_NUM = 10;
    private static final int LOOP_COUNT = 1000;

    @Test
    void counterTest() {
        final Runnable loop = () -> {
            for (int i = 0; i < LOOP_COUNT; i++)
                counter.increment();
        };

        List<Thread> threads = Stream.generate(() -> new Thread(loop))
            .limit(T_NUM)
                .collect(Collectors.toList());

        threads.forEach(Thread::start);

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Assertions.assertEquals(counter.getValue(), (long) T_NUM * LOOP_COUNT);
    }

}
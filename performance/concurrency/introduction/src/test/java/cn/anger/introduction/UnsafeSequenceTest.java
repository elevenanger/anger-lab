package cn.anger.introduction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : anger
 */
class UnsafeSequenceTest {
    final UnsafeSequence sequence = new UnsafeSequence();

    @Test
    void concurrencyTest() throws InterruptedException {
        final Runnable runnable = () -> {
            for (int i = 0; i < 10_000; i++) {
                sequence.getNext();
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(runnable));
        }

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            thread.join();
        }

        Assertions.assertNotEquals(sequence.getNext(), 100_000);

    }

}
package aqs;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
class OneShotLatchTest {

    @Test
    void testOneShotLatch() {
        OneShotLatch latch = new OneShotLatch();
        Runnable waitLatch = () -> {
            try {
                System.out.println(Thread.currentThread().getId() + " begin.");
                latch.await();
                System.out.println(Thread.currentThread().getId() + " finished.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        List<Thread> threads = Stream.generate(() -> new Thread(waitLatch))
                                .limit(10)
                                .collect(Collectors.toList());

        threads.forEach(Thread::start);

        latch.signal();

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

}
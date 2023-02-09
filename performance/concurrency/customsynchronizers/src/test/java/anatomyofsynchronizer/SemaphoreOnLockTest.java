package anatomyofsynchronizer;

import cn.anger.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SemaphoreOnLockTest {

    @Test
    void testSemaphoreLock() {
        SemaphoreOnLock semaphore = new SemaphoreOnLock(5);

        List<Thread> threads = Stream.generate(() -> new SemaphoreThread(semaphore))
            .limit(10)
            .collect(Collectors.toList());
        ThreadUtil.startAndJoin(threads);
    }

    static class SemaphoreThread extends Thread {
        private final SemaphoreOnLock semaphore;

        public SemaphoreThread(SemaphoreOnLock semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getId());
                ThreadUtil.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                semaphore.release();
            }
        }
    }

}
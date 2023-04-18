package labutils.synchronizers.latches;

import java.util.concurrent.CountDownLatch;

/**
 * @author : anger
 */
public class TestHarness {
    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread thread = new Thread(() -> {
                try {
                    task.run();
                } finally {
                    end.countDown();
                }
            });
            thread.start();
        }
        long startTime = System.nanoTime();
        start.countDown();
        end.await();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}

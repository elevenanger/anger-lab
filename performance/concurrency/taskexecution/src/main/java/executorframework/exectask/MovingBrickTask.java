package executorframework.exectask;

import cn.anger.concurrency.ThreadUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : anger
 */
public class MovingBrickTask implements ExpensiveTask {
    private final AtomicLong workCount = new AtomicLong(0);

    @Override
    public void perform(Executor executor, int workload) {
        CountDownLatch latch = new CountDownLatch(workload);
        long startTime = System.nanoTime();

        for (int i = 0; i < workload; i++)
            executor.execute(() -> moveBrick(latch));

        try {
            latch.await();
            System.out.printf("Task finished time cost => %d ms%n", (System.nanoTime() - startTime) / 1_000_000);
        } catch (InterruptedException e) {
            System.out.println(e.getLocalizedMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void moveBrick(CountDownLatch latch) {
        ThreadUtil.sleep(1);
        synchronized (workCount) {
            System.out.printf("%s moved one brick, total moved => %d%n",
                Thread.currentThread().getName(),
                workCount.incrementAndGet());
            latch.countDown();
        }
    }

}

package saturation;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * @author : anger
 * 使用 Semaphore 来限制任务的提交速率
 * 在这种方法中可以使用无界队列（不需要同时限制队列大小和任务注入速率）
 * 信号量的大小等于线程池的大小加上允许排队的任务数量
 * 因为信号量同时限制了提交等待的任务数和并发执行的任务数
 */
public class BoundedExecutor {
    private final Executor executor;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor executor, int bound) {
        this.executor = executor;
        this.semaphore = new Semaphore(bound);
    }

    /**
     * 提交任务
     * @param command 任务执行指令
     * @throws InterruptedException
     */
    public void submitTask(final Runnable command) throws InterruptedException {
        final String worker = Thread.currentThread().getName();
        // 获取信号量
        semaphore.acquire();
        try {
            executor.execute(() -> {
                try {
                    System.out.printf("%s running task.%n", worker);
                    command.run();
                } finally {
                    // 任务执行完成后释放信号量
                    semaphore.release();
                    System.out.printf("task finished %s release semaphore%n", worker);
                }
            });
        } catch (RejectedExecutionException e) {
            // 任务被拒绝也释放信号量
            semaphore.release();
            System.out.printf("task rejected %s release semaphore%n", worker);
        }
    }
}

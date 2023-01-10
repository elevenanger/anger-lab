package stopthreadbasedservice.limitationofshutdownnow;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : anger
 * 封装 ExecutorService 用于追踪 shutdown 之后取消的任务
 */
public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService exec;
    private final Set<Runnable> tasksCancelledAndShutdown =
        Collections.synchronizedSet(new HashSet<>());

    public TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
    }

    @Override
    public void shutdown() {
        exec.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout, unit);
    }

    public List<Runnable> getCancelledTasks() {
        if (!exec.isTerminated())
            throw new IllegalStateException("executor still running.");
        return new ArrayList<>(tasksCancelledAndShutdown);
    }

    /**
     * 执行任务
     * 如果任务执行的时候 Executor 或者执行线程已经终止
     * 则将任务添加到已取消的任务列表中
     * @param command the runnable task
     */
    @Override
    public void execute(Runnable command) {
        exec.execute(() -> {
            try {
                command.run();
            } finally {
                if (isShutdown() && Thread.currentThread().isInterrupted())
                    tasksCancelledAndShutdown.add(command);
            }
        });
    }
}

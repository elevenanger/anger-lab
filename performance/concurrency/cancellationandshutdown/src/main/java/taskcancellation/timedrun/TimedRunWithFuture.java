package taskcancellation.timedrun;

import cn.anger.util.exception.LaunderThrowable;

import java.util.concurrent.*;

/**
 * @author : anger
 * 通过 Executor 创建任务返回的 Future
 * 使用 Future 获取结果
 */
public class TimedRunWithFuture {
    private static final ExecutorService taskExec =
        Executors.newFixedThreadPool(2);

    public static void timedRun(Runnable r, long timeout, TimeUnit timeUnit)
        throws InterruptedException {
        // 提交任务
        Future<?> task = taskExec.submit(r);
        try {
            // 在超时时间内获取结果
            task.get(timeout, timeUnit);
        } catch (ExecutionException e) {
            // 重新抛出任务执行中的抛出的异常
            throw LaunderThrowable.launderThrowable(e.getCause());
        } catch (TimeoutException e) {
            // 在 finally 中取消任务
        } finally {
            System.out.println("cancel task");
            // 因为是标准的 Executor 创建的任务，可以取消
            task.cancel(true);
        }
    }
}

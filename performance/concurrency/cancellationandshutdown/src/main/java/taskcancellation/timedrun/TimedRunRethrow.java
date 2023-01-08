package taskcancellation.timedrun;

import cn.anger.exception.LaunderThrowable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : anger
 * 在代码中创建新的线程使用新的线程执行任务，
 * 保存任务执行的异常重新抛出异常，
 * 因为这个新的线程是代码中创建的，
 * 所以可以对其进行终止，
 * 但是存在一个问题就是依赖 join 方法
 * 我们不知道控制逻辑返回是因为线程正常结束还是 join 方法超时
 */
public class TimedRunRethrow {
    private static final ScheduledExecutorService cancelExec =
        Executors.newScheduledThreadPool(1);
    public static void timedRun(final Runnable r, long timeout, TimeUnit unit)
        throws InterruptedException {
        class RethrowTask implements Runnable {
            // volatile 确保异常在多个线程之间的可见性
            private volatile Throwable t;
            public void run() {
                try {
                    r.run();
                } catch (Throwable throwable) {
                    // 将任务处理的异常存储在 t 中
                    t = throwable;
                }
            }

            void rethrow() {
                if (t != null)
                    throw LaunderThrowable.launderThrowable(t);
            }
        }

        RethrowTask task = new RethrowTask();
        // 使用一个新的线程执行任务
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(taskThread::interrupt, timeout, unit);
        // 任务结束后 join 当前线程
        taskThread.join(unit.toMillis(timeout));
        // 如果有异常则抛出存储的异常
        task.rethrow();
    }
}

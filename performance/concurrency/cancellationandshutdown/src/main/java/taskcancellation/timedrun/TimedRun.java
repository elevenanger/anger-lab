package taskcancellation.timedrun;

import cn.anger.concurrency.ThreadUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : anger
 * 启用一个独立的线程处理任务
 * 到时间终止线程
 * 但是这违反了一个原则：
 * 如果不知道一个线程的终止策略，则不能终止这个线程
 * 因为 timedRun 可以由任意的线程调用，
 * 所以它不可能直到线程的终止策略
 */
public class TimedRun {
    private static final ScheduledExecutorService exec =
        Executors.newScheduledThreadPool(1);

    public static void timedRun(Runnable r, long timeout, TimeUnit timeUnit) {
        // 获取当前线程
        final Thread taskThread = Thread.currentThread();
        // 到时候终止线程
        exec.schedule(taskThread::interrupt, timeout, timeUnit);
        // 执行任务
        r.run();
        ThreadUtil.sleep(100);
    }
}

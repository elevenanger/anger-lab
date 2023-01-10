package handleabnormalthreadtermination.threadpoolworker;

import javax.annotation.Nullable;
import java.util.concurrent.BlockingQueue;

/**
 * @author : anger
 * 典型的线程池工作线程架构
 */
public class PoolWorker extends Thread {

    private final BlockingQueue<Runnable> workingQueue;

    public PoolWorker(BlockingQueue<Runnable> workingQueue) {
        this.workingQueue = workingQueue;
    }

    private void runTask(Runnable runnable) {
        runnable.run();
    }

    private Runnable getTaskFromWorkQueue() throws InterruptedException {
        return workingQueue.take();
    }

    private void threadExited(Thread thread, @Nullable Throwable throwable) {
        if (throwable != null) {
            System.out.println("Thread exit dealing with exception.");
            throwable.printStackTrace();
        }
        thread.interrupt();
    }

    @Override
    public void run() {
        Throwable thrown = null;
        try {
            while (!isInterrupted())
                runTask(getTaskFromWorkQueue());
        } catch (Throwable throwable) {
            // 捕获异常，赋值给 thrown
            thrown = throwable;
        } finally {
            // 线程退出需要执行的操作，打印错误信息等
            threadExited(this, thrown);
        }
    }
}

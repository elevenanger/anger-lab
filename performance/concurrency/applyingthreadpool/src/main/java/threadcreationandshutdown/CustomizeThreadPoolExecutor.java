package threadcreationandshutdown;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 * 自定义线程池
 */
public class CustomizeThreadPoolExecutor {

    private CustomizeThreadPoolExecutor() {}

    public static ThreadPoolExecutor customizeThreadPool() {
        Executors.newSingleThreadExecutor();
        return new ThreadPoolExecutor(
            1,
            Runtime.getRuntime().availableProcessors(),
            1000,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1000),
            new FixedSizeThreadFactory(10),
            new ThreadPoolExecutor.AbortPolicy()
        );
    }

    private static class FixedSizeThreadFactory implements ThreadFactory {
        private final int maxThread;

        private final AtomicInteger count = new AtomicInteger(0);

        public FixedSizeThreadFactory(int maxThread) {
            this.maxThread = maxThread;
        }

        @Override
        public Thread newThread(Runnable r) {
            if (count.incrementAndGet() > maxThread) {
                count.decrementAndGet();
                return null;
            }
            return new Thread(r);
        }
    }
}

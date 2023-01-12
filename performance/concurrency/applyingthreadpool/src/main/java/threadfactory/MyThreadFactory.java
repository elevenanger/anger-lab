package threadfactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : anger
 * 自定义 ThreadFactory
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyThread(r, poolName);
    }

    private static class MyThread extends Thread {
        private static final String DEFAULT_NAME = "MyApp";
        private static volatile boolean debugLifecycle = true;
        private static final AtomicInteger created = new AtomicInteger();
        private static final AtomicInteger alive = new AtomicInteger();
        private static final Logger logger = Logger.getAnonymousLogger();

        public MyThread(Runnable target) {
            this(target, DEFAULT_NAME);
        }

        public MyThread(Runnable target, String name) {
            super(target, name + "-" + created.incrementAndGet());
            setUncaughtExceptionHandler(
                (t, e) -> {
                    logger.log(Level.SEVERE,
                        "Uncaught in thread " + t.getName(), e);
                }
            );
        }

        @Override
        public void run() {
            boolean debug = debugLifecycle;
            if (debug)
                logger.log(Level.INFO, "created " + getName());
            try {
                alive.incrementAndGet();
                super.run();
            } finally {
                alive.decrementAndGet();
                if (debug)
                    logger.log(Level.INFO, "exiting " + getName());
            }
        }
    }
}

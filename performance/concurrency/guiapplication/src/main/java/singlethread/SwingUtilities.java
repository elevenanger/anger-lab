package singlethread;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

/**
 * @author : anger
 * 单线程 Swing 框架
 */
public class SwingUtilities {
    private static final ExecutorService exec =
        Executors.newSingleThreadExecutor();

    private static volatile Thread swingThread;

    private static class SwingThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            swingThread = new Thread(r);
            return swingThread;
        }
    }

    public static boolean isEventDispatchThread() {
        return Thread.currentThread() == swingThread;
    }

    public static void invokeLater(Runnable task) {
        exec.execute(task);
    }

    public static void invokeAndAwait(Runnable task)
    throws InterruptedException, InvocationTargetException {
        Future<?> f = exec.submit(task);
        try {
            f.get();
        } catch (ExecutionException e) {
            throw new InvocationTargetException(e);
        }
    }
}

package cn.anger.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
public class ThreadUtil {
    private ThreadUtil() {}
    private static final long TICK_TIME = 50;

    /**
     * 线程休眠
     * @param millis 休眠时间
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 线程休眠 50 毫秒
     */
    public static void sleep() {
        try {
            Thread.sleep(TICK_TIME);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 创建通用的工作线程池
     * 传入一个 Runnable 对象和线程数量
     * 所有线程接收同一个 runnable 运行
     * @param threadNum 线程数
     * @param runnable 需要执行的工作
     * @return 线程列表
     */
    public static List<Thread> commonWorkingThreadPool (int threadNum, Runnable runnable) {
        return Stream.generate(() -> new Thread(runnable))
            .limit(threadNum)
            .collect(Collectors.toList());
    }

    /**
     * 线程批量 join
     * @param threads 线程池
     */
    public static void join(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void startAndJoin(Thread thread) {
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 线程批量 start 和 join
     * @param threads 线程列表
     */
    public static void startAndJoin(List<Thread> threads) {
        // 开始执行和结束执行的 latch
        // startGate count 设置为 1
        CountDownLatch startGate = new CountDownLatch(1);
        // endGate count 设置为线程数
        CountDownLatch endGate = new CountDownLatch(threads.size());

        // 计时，startGate countDown
        startGate.countDown();
        long start = System.nanoTime();
        // Thread.start 表示执行开始
        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            try {
                // 每一个线程完成 join
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                // endGate countDown
                endGate.countDown();
            }
        }

        try {
            // 等待所有线程 join ，count 减少到 0
            endGate.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long end = System.nanoTime();
        System.out.printf("time elapsed => %.2f ms%n", (float) (end - start) / 1_000_000);
    }

    public static void startAndJoin(Thread ... threads) {
        startAndJoin(Arrays.asList(threads));
    }

    /**
     * 返回重复执行一段代码逻辑的 Runnable
     * @param loopNum 循环次数
     * @param work 需要重复执行的代码逻辑
     * @return Runnable 对象
     */
    public static Runnable loopWork(int loopNum, Consumer<Void> work) {
        return () -> {
            for (int i = 0; i < loopNum; i++)
                work.accept(null);
        };
    }

}

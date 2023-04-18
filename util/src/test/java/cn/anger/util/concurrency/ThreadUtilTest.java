package cn.anger.util.concurrency;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
class ThreadUtilTest {

    @Test
    void sleep() {
        ThreadUtil.sleep(100);
    }

    @Test
    void testSleep() {
        ThreadUtil.sleep();
    }

    @Test
    void commonWorkingThreadPool() {
        List<Thread> threads = ThreadUtil.commonWorkingThreadPool(10,
            () -> System.out.println(Thread.currentThread().getName()));
        threads.forEach(Thread::start);
    }

    @Test
    void join() {
        List<Thread> threads = ThreadUtil.commonWorkingThreadPool(10,
            () -> System.out.println(Thread.currentThread().getName()));
        threads.forEach(Thread::start);
        ThreadUtil.join(threads);
    }

    @Test
    void startAndJoin() {
        List<Thread> threads = ThreadUtil.commonWorkingThreadPool(10,
            () -> System.out.println(Thread.currentThread().getName()));
        ThreadUtil.startAndJoin(threads);
    }

    @Test
    void testLoopWorkFunction() {
        AtomicInteger count = new AtomicInteger(0);
        Runnable runnable = ThreadUtil
            .loopWork(10, unused -> System.out.println(count.getAndIncrement()));
        ThreadUtil.startAndJoin(new Thread(runnable));
    }
}
package threadfactory;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;
import threadcreationandshutdown.CustomizeThreadPoolExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : anger
 */
class MyThreadFactoryTest {

    @Test
    void testMyThreadFactory() {
        MyThreadFactory factory = new MyThreadFactory("anger");

        ThreadPoolExecutor executor =
            CustomizeThreadPoolExecutor.customizeThreadPool();
        executor.setThreadFactory(factory);

        ConcurrentWorkStream
            .commonWorkStream(() ->
                executor.submit(() -> System.out.printf("worker => %s%n", Thread.currentThread().getName())))
            .doWork();
    }
}
package threadcreationandshutdown;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
class CustomizeThreadPoolExecutorTest {

    @Test
    void customizeThreadPoolExecutorTest() throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        Runnable task = () ->
            System.out.printf("%s count %d%n", Thread.currentThread().getName(), count.incrementAndGet());

        ExecutorService exec = CustomizeThreadPoolExecutor.customizeThreadPool();

        ConcurrentWorkStream.commonWorkStream(() -> exec.submit(task)).doWork();

        exec.awaitTermination(200, TimeUnit.MILLISECONDS);

        Assertions.assertEquals(1000, count.get());
    }
}
package handleabnormalthreadtermination.threadpoolworker;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
class PoolWorkerTest {

    @Test
    void workerTest() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);

        Runnable task = () -> System.out.printf("task %d%n", integer.incrementAndGet());
        Runnable uncaughtExceptionTask = () -> {
            System.out.println("exception.");
            throw new RuntimeException();
        };

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        queue.put(task);
        queue.put(task);
        queue.put(task);
        queue.put(task);
        queue.put(uncaughtExceptionTask);

        PoolWorker worker = new PoolWorker(queue);
        worker.start();
    }

}
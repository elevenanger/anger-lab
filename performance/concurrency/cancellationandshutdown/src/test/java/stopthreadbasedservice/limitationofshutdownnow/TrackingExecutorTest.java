package stopthreadbasedservice.limitationofshutdownnow;

import cn.anger.util.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class TrackingExecutorTest {

    private final AtomicInteger count = new AtomicInteger(0);

    @Test
    void testTrackingCancelledTasks() {
        Runnable task = () -> {
            ThreadUtil.sleep(1000);
            System.out.println(count.incrementAndGet());
        };

        List<Runnable> tasks = Arrays.asList(task, task, task);

        TrackingExecutor executor = new TrackingExecutor(Executors.newSingleThreadExecutor());
        tasks.forEach(executor::execute);
        List<Runnable> cancelledTasks = executor.shutdownNow();
        assertNotEquals(0, cancelledTasks.size());
        assertNotEquals(0, executor.getCancelledTasks().size());
    }
}
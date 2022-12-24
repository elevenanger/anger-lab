package executorframework.exectask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

import static java.util.concurrent.Executors.*;

/**
 * @author : anger
 */
class ExpensiveTaskHandlerTest {
    private final static int WORKLOAD = 1_000;
    private final static int POOL_SIZE = 20;
    private final ExpensiveTaskHandler handler = new ExpensiveTaskHandler();

    @BeforeEach
    void setUp() {
        handler.setTask(new MovingBrickTask());
        handler.setWorkload(WORKLOAD);
    }

    private final Consumer<Executor> setExecutorToHandleTask = executor -> {
        handler.setExecutor(executor);
        handler.handleTask();
    };

    @Test
    void testFixedThreadPool() {
        setExecutorToHandleTask.accept(newFixedThreadPool(POOL_SIZE));
    }

    @Test
    void testSingleThreadExecutor() {
        setExecutorToHandleTask.accept(newSingleThreadExecutor());
    }

    @Test
    void testCachedThreadPool() {
        setExecutorToHandleTask.accept(newCachedThreadPool());
    }

    @Test
    void testScheduledThreadPool() {
        setExecutorToHandleTask.accept(newScheduledThreadPool(POOL_SIZE));
    }

    @Test
    void testWorkStealingThreadPool() {
        setExecutorToHandleTask.accept(newWorkStealingPool());
    }

}
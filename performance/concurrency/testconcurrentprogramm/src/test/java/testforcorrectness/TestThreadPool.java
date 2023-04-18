package testforcorrectness;

import cn.anger.util.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : anger
 */
class TestThreadPool {

    private final TestThreadFactory threadFactory = new TestThreadFactory();

    /**
     * 测试线程的创建数量符合预期
     */
    @Test
    void testPoolExpansion() {
        int MAX_SIZE = 10;
        ExecutorService exec =
            Executors.newFixedThreadPool(MAX_SIZE);

        ((ThreadPoolExecutor) exec).setThreadFactory(threadFactory);

        for (int i = 0; i < 10 * MAX_SIZE; i++)
            exec.execute(() -> ThreadUtil.sleep(Integer.MAX_VALUE));

        for (int i = 0; i < 20 && threadFactory.numCreated.get() < MAX_SIZE; i++)
            ThreadUtil.sleep(100);

        assertEquals(threadFactory.numCreated.get(), MAX_SIZE);
        exec.shutdownNow();
    }
}
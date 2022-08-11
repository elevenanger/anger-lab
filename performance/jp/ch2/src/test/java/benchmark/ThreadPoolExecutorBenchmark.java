package benchmark;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * author : liuanglin
 * date : 2022/8/8 17:14
 * description : 线程池处理器测试
 */
@Slf4j
class ThreadPoolExecutorTest {

    @Test
    void synchronousQueue() {
        Executor executor = Executors.newCachedThreadPool();
        // 同步队列
        executor = Executors.newFixedThreadPool(10);
        // 无界队列
        executor = Executors.newSingleThreadScheduledExecutor();
        // 自定义线程池
        executor = new ThreadPoolExecutor(1, 10,
                                1L, TimeUnit.SECONDS,
                                    new ArrayBlockingQueue<>(20));
        executor = new ForkJoinPool();

    }
}

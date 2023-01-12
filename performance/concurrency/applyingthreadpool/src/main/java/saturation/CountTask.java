package saturation;

import threadcreationandshutdown.CustomizeThreadPoolExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * @author : anger
 * 使用 RejectionHandler 定义队列满的时候提交新任务的处理策略
 */
public class CountTask {

    private final ThreadPoolExecutor exec =
        CustomizeThreadPoolExecutor.customizeSingleThreadPool();

    public CountTask(RejectedExecutionHandler policy) {
        exec.setRejectedExecutionHandler(policy);
    }

    public void doCount(int count) {
        final IntConsumer intCount =
            i -> exec.submit(() -> System.out.printf("count %d%n", i));

        IntStream.rangeClosed(0, count)
            .parallel()
            .forEach(intCount);

        exec.shutdown();
    }

    public static void main(String[] args) {
        // 自定义 RejectionHandler
        CountTask task = new CountTask((runnable, executor) -> System.out.println("ignored"));
        task.doCount(100);
    }

}

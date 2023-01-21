package thinkingaboutperformance.amdahl;

import cn.anger.concurrency.ThreadUtil;
import cn.anger.timer.stopwatch.StopWatch;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
class WorkerThreadTest {
    final List<Runnable> tasks = Stream
        .generate(() -> (Runnable) () -> ThreadUtil.sleep(10))
        .limit(100)
        .collect(Collectors.toList());

    final StopWatch stopWatch = StopWatch.get();

    BlockingQueue<Runnable> workingTasks;

    void testWorkerThread(int threadNum) {
        workingTasks = new LinkedBlockingQueue<>(tasks);

        List<WorkerThread> threads =
            Stream.generate(() -> new WorkerThread(workingTasks))
                .limit(threadNum)
                .collect(Collectors.toList());

        stopWatch.start(threadNum + " workers");
        threads.stream().parallel().forEach(WorkerThread::run);
        stopWatch.stopAndPrint();
    }

    @Test
    void testScalability() {
        testWorkerThread(Runtime.getRuntime().availableProcessors());
        testWorkerThread(1);
        testWorkerThread(2);
        testWorkerThread(5);
        testWorkerThread(20);
    }
}
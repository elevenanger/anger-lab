package extendingthreadpool;

import org.junit.jupiter.api.Test;
import threadcreationandshutdown.CustomizeThreadPoolExecutor;
import threadpoolexecutors.PrintInfoExec;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : anger
 */
class TimingThreadPoolTest {

    @Test
    void timingThreadPoolTest() {
        ThreadPoolExecutor threadPool = CustomizeThreadPoolExecutor.newTimingThreadPool();

        PrintInfoExec exec = new PrintInfoExec(threadPool);
        exec.printInfo("something", 100);

        threadPool.shutdown();
    }

}
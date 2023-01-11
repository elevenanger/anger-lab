package threadpoolexecutors;

import org.junit.jupiter.api.Test;
import threadcreationandshutdown.CustomizeThreadPoolExecutor;

import java.util.concurrent.Executors;

/**
 * @author : anger
 */
class PrintInfoExecTest {
    private static final int TIMES = 1000;
    private PrintInfoExec exec;

    @Test
    void testSingleThreadExecutorPrintInfo() {
        exec = new PrintInfoExec(Executors.newSingleThreadExecutor());
        exec.printInfo("single thread executor test", TIMES);
    }

    @Test
    void testCachedThreadPoolExecutorPrintInfo() {
        exec = new PrintInfoExec(Executors.newCachedThreadPool());
        exec.printInfo("cached thread pool test", TIMES);
    }

    @Test
    void testFixedSizedThreadPoolPrintInfo() {
        exec = new PrintInfoExec(Executors.newFixedThreadPool(10));
        exec.printInfo("fixed thread pool test", TIMES);
    }

    @Test
    void testWorkStealingThreadPoolPrintInfo() {
        exec = new PrintInfoExec(Executors.newWorkStealingPool());
        exec.printInfo("work stealing pool test", TIMES);
    }

    @Test
    void testCustomizeThreadPoolPrintInfo() {
        exec = new PrintInfoExec(CustomizeThreadPoolExecutor.customizeThreadPool());
        exec.printInfo("customize thread pool test", TIMES);
    }

}
package cn.anger.util.timer.stopwatch;

import cn.anger.util.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class StopWatchTest {

    @Test
    void testStopWatch() {
        StopWatch watch = StopWatch.get();
        watch.start("test");
        ThreadUtil.sleep(100);
        watch.stop();
        watch.printInfo();
    }

}
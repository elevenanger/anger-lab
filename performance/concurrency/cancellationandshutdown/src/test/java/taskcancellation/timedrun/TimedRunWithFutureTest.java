package taskcancellation.timedrun;

import cn.anger.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author : anger
 */
class TimedRunWithFutureTest {

    private volatile int i  = 0;

    @Test
    void testTimedRun() throws InterruptedException {
        TimedRunWithFuture.timedRun(() -> {
            ThreadUtil.sleep(2000);
        }, 100, TimeUnit.MILLISECONDS);
    }

}
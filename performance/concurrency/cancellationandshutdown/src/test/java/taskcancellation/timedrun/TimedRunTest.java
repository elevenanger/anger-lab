package taskcancellation.timedrun;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author : anger
 */
class TimedRunTest {

    @Test
    void testTimedRun() throws InterruptedException {
        TimedRun.timedRun(() -> System.out.println("timed run"),
            100, TimeUnit.MILLISECONDS);
        Assertions.assertTrue(Thread.currentThread().isInterrupted());
    }

}
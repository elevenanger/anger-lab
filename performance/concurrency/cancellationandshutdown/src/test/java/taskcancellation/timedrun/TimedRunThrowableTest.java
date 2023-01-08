package taskcancellation.timedrun;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author : anger
 */
class TimedRunThrowableTest {

    @Test
    void testTimedRun() throws InterruptedException {
        TimedRunRethrow.timedRun(() -> System.out.println("timed out"),
            100, TimeUnit.MILLISECONDS);
    }

}
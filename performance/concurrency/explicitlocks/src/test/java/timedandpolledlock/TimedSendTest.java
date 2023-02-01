package timedandpolledlock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class TimedSendTest {

    @Test
    void testSendOnSharedLine() throws InterruptedException {
        TimedSend timedSend = new TimedSend();
        assertTrue(timedSend.trySendOnSharedLine("timed send", 100, TimeUnit.NANOSECONDS));
    }

}
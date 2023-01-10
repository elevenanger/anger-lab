package taskcancellation.oneshotexecution;

import org.junit.jupiter.api.Test;
import stopthreadbasedservice.oneshotexecution.CheckForMail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author : anger
 */
class CheckForMailTest {
    @Test
    void checkMailTest() throws InterruptedException {
        CheckForMail checkForMail = new CheckForMail();
        boolean checked = checkForMail.checkMail(
            new HashSet<>(
                Arrays.asList(
                    "127.0.0.1:8081",
                    "127.0.0.1:8082",
                    "127.0.0.1:8083",
                    "127.0.0.1:8084"
                )
            ), 1000, TimeUnit.MILLISECONDS);
        assertTrue(checked);
    }

}
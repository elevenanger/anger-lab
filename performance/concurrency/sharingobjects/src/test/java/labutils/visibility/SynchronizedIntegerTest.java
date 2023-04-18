package labutils.visibility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class SynchronizedIntegerTest {

    private final SynchronizedInteger integer = new SynchronizedInteger();

    @Test
    void staleValueTest() {
        new Thread(() -> {
            integer.setValue(1);
        }).start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(1, integer.getValue());
    }

}
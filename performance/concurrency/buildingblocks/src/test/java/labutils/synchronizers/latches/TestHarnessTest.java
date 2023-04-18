package labutils.synchronizers.latches;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class TestHarnessTest {

    @Test
    void testTimeTasks() throws InterruptedException{
        long time = TestHarness.timeTasks(10, () -> {
            System.out.println(System.nanoTime());
        });
        System.out.println((float) time/1_000_000);
    }

}
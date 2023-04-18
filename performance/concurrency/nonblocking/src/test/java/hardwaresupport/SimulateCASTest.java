package hardwaresupport;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author : anger
 */
class SimulateCASTest {

    @Test
    void testCompareAndSet() {
        SimulateCAS cas = new SimulateCAS();
        assertTrue(cas.compareAndSet(0, 10));
        assertEquals(10, cas.getValue());
    }

    @Test
    void testConcurrentCompareAndSet() {
        SimulateCAS cas = new SimulateCAS();
        ConcurrentWorkStream.initialize()
            .withWorkLoadConfig(new ConcurrentWorkStream.WorkLoadConfig(10,  1))
            .setTask(() -> {
                boolean result = cas.compareAndSet(0, 10);
                System.out.println(Thread.currentThread().getId() + " " + result);
            }).doWork();
        assertEquals(10, cas.getValue());
    }

}
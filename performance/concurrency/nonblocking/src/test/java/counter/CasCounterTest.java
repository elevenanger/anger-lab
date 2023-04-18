package counter;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class CasCounterTest {

    @Test
    void testIncrement() {
        CasCounter counter = new CasCounter();
        ConcurrentWorkStream.commonWorkStreams(counter::increment).doWork();
        assertEquals(1000, counter.getValue());
    }


}
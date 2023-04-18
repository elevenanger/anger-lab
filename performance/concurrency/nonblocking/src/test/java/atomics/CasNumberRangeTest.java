package atomics;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import cn.anger.util.random.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class CasNumberRangeTest {
    @Test
    void testSetAndGetLower() {
        CasNumberRange range = new CasNumberRange();
        range.setLower(-1);
        assertEquals(-1, range.getLower());
    }

    @Test
    void testSetAndGetUpper() {
        CasNumberRange range = new CasNumberRange();
        range.setUpper(1);
        assertEquals(1, range.getUpper());
    }

    @Test
    void testRaceCondition() {
        CasNumberRange range = new CasNumberRange();
        ConcurrentWorkStream.commonWorkStreams(
            () -> range.setLower(Random.getRandomWithLimit(1_000)),
            () -> range.setUpper(Random.getRandomWithLimit(1_000))
        ).doWork();

        assertTrue(range.getLower() <= range.getUpper());
    }
}
package labutils.lazyinit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class LazyInitSynchronizedTest extends LazyInitTest<LazyInitSynchronized> {
    @Override
    LazyInitSynchronized initializeLazyObject() {
        return new LazyInitSynchronized();
    }

    @Test
    @Override
    void doLazyInitRaceConditionTest() {
        prepareLazyInitRaceCondition();
        assertSame(expensiveInstance1, expensiveInstance2);
    }
}
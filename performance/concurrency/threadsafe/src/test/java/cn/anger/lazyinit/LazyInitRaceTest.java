package cn.anger.lazyinit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class LazyInitRaceTest extends LazyInitTest<LazyInitRace> {
    @Override
    LazyInitRace initializeLazyObject() {
        return new LazyInitRace();
    }

    @Test
    @Override
    void doLazyInitRaceConditionTest() {
        prepareLazyInitRaceCondition();
        assertNotSame(expensiveInstance1, expensiveInstance2);
    }
}
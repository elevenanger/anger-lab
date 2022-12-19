package cn.anger.hiddeniterators;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class HiddenIteratorTest {
    private final HiddenIterator hidden = new HiddenIterator();

    @Test
    void testHiddenIterator() {
        hidden.addTenThings();
    }
}
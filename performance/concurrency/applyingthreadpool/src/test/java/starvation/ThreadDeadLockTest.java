package starvation;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class ThreadDeadLockTest {

    @Test
    void testDeadLock() throws Exception {
        ThreadDeadLock lock = new ThreadDeadLock();
        lock.printPageInfo();
    }
}
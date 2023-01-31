package lockandreentrantlock;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class CanonicalLockUsageTest {

    private final CanonicalLockUsage lockUseage =
        new CanonicalLockUsage(new ReentrantLock());

    @Test
    void testUsage() {
        ConcurrentWorkStream.heavyWorkStream(lockUseage::usage).doWork();
        assertEquals(200_000, lockUseage.count);
    }
}
package taskcancellation.timedrun;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : anger
 */
class NonInterruptibleBlockTest {

    @Test
    void testSelector() {
        try (Selector selector = Selector.open()){
            selector.select();
            selector.wakeup();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testLockAcquire() {
        Lock lock = new ReentrantLock();
        new Thread(lock::lock).start();
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

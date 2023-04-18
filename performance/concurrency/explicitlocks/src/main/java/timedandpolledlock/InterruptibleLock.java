package timedandpolledlock;

import cn.anger.util.concurrency.ThreadUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : anger
 */
public class InterruptibleLock {
    private final Lock lock = new ReentrantLock();

    public boolean sendOnSharedLine(String message)
        throws InterruptedException {
        lock.lockInterruptibly();
        try {
            return cancellableSendOnSharedLine(message);
        } finally {
            lock.unlock();
        }
    }

    private boolean cancellableSendOnSharedLine(String message)
        throws InterruptedException {
        ThreadUtil.sleep(10_000);
        System.out.println("sent message => " + message);
        return true;
    }

    public static void main(String[] args) {
        final InterruptibleLock interruptibleLock = new InterruptibleLock();
        Thread t1 = new Thread(() -> {
            try {
                interruptibleLock.sendOnSharedLine("lock");
            } catch (InterruptedException e) {
                System.out.printf("thread %s interrupted.", Thread.currentThread().getName());
            }
        });
        t1.start();
        t1.interrupt();
    }
}

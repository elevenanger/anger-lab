package aqs;

import labutils.annotation.ThreadSafe;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author : anger
 * 使用 AQS 实现的 Latch
 */
@ThreadSafe
public class OneShotLatch {

    private final Sync sync = new Sync();

    /**
     * 释放 latch
     * 使得所有阻塞的线程可以继续执行
     */
    public void signal() {
        sync.releaseShared(0);
    }

    /**
     * 获取方法，在 sync 释放之前，阻塞线程
     */
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int ignored) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int ignored) {
            setState(1);
            return true;
        }
    }
}

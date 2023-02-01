package timedandpolledlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : anger
 */
public class TimedSend {

    private final Lock lock = new ReentrantLock();

    public boolean trySendOnSharedLine(String message,
                                       long timeout,
                                       TimeUnit unit) throws InterruptedException {
        // 预估的活动执行时间
        long nanosToSend = 50;
        // 锁定时间 = 超时时间（时间预算） - 活动执行时间
        long nanosToLock = unit.toNanos(timeout) - nanosToSend;
        if (!lock.tryLock(nanosToLock, TimeUnit.NANOSECONDS))
            return false;
        try {
            return sendOnSharedLine(message);
        } finally {
            lock.unlock();
        }
    }

    private boolean sendOnSharedLine(String message) {
        System.out.println("sent message => " + message);
        return true;
    }

}

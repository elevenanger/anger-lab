package cn.anger.synchronizers.latches;

import java.util.concurrent.CountDownLatch;

/**
 * @author : anger
 */
public class TroubleSleeping implements Runnable {
    private final CountDownLatch sleepLatch;

    public TroubleSleeping(CountDownLatch sleepLatch) {
        this.sleepLatch = sleepLatch;
    }

    @Override
    public void run() {
        try {
            sleepLatch.await();
            System.out.println("zzz...");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

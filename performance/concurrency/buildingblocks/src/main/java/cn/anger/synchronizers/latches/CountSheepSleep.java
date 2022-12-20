package cn.anger.synchronizers.latches;

import java.util.concurrent.CountDownLatch;

/**
 * @author : anger
 */
public class CountSheepSleep {
    private static final Integer SHEEP_NUM = 100;
    public static void countToSleep() {
        CountDownLatch latch = new CountDownLatch(SHEEP_NUM);

        CountSheep countSheep = new CountSheep(latch, SHEEP_NUM);
        TroubleSleeping sleeping = new TroubleSleeping(latch);

        new Thread(countSheep).start();
        new Thread(sleeping).start();
    }

    public static void main(String[] args) {
        countToSleep();
    }
}

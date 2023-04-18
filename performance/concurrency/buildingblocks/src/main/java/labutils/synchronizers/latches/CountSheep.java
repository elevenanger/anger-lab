package labutils.synchronizers.latches;

import java.util.concurrent.CountDownLatch;

/**
 * @author : anger
 * 演示 CountDownLatch 的用法
 */
public class CountSheep implements Runnable {
    private final CountDownLatch countSheep;
    private final Integer sheepNum;

    public CountSheep(CountDownLatch countSheep, Integer sheepNum) {
        this.countSheep = countSheep;
        this.sheepNum = sheepNum;
    }

    @Override
    public void run() {
        for (int i = 1; i <= sheepNum; i++) {
            System.out.println(i + " sheep");
            countSheep.countDown();
        }
    }
}

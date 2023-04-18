package deadlock.lockordering;

import cn.anger.util.concurrency.ThreadUtil;

/**
 * @author : anger
 * 顺序死锁
 * 不同线程按照不同的顺序获取锁
 * 可能会导致顺序死锁
 */
public class LeftRightDeadlock {
    private static final Object left = new Object();
    private static final Object right = new Object();

    /**
     * 先获取 left 再获取 right 的锁
     */
    public void leftRight() {
        synchronized (left) {
            System.out.println("get left");
            ThreadUtil.sleep(100);
            synchronized (right) {
                System.out.println("get left and right");
            }
        }
    }

    /**
     * 先获取 right 再获取 left 的锁
     */
    public void rightLeft() {
        synchronized (right) {
            System.out.println("get right");
            ThreadUtil.sleep(100);
            synchronized (left) {
                System.out.println("get right and left");
            }
        }
    }

    private static class LeftRightThread extends Thread {
        @Override
        public void run() {
            new LeftRightDeadlock().leftRight();
        }
    }

    private static class RightLeftThread extends Thread {
        @Override
        public void run() {
            new LeftRightDeadlock().rightLeft();
        }
    }

    public static void main(String[] args) {
        new LeftRightThread().start();
        new RightLeftThread().start();
    }
}

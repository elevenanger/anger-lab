package cn.anger.visibility;

/**
 * @author : anger
 * 不使用同步机制
 * 无法确保跨线程内存写入的可见性
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    static void getReady() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ready = true;
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        getReady();
    }
}

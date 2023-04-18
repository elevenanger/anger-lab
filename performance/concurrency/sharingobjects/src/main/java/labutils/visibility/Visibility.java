package labutils.visibility;

/**
 * @author : anger
 */
public class Visibility {
    private static volatile boolean ready;
    private static volatile int number;

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

package taskcancellation;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * @author : anger
 */
public class FixedPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    public FixedPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            // 使用 Thread 的 interrupt 状态进行判断线程是否正常
            while (!Thread.currentThread().isInterrupted())
                queue.put(p = p.nextProbablePrime());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void cancel() {
        // 调用 Thread interrupt() 方法来取消当前线程正在执行的操作
        interrupt();
    }
}

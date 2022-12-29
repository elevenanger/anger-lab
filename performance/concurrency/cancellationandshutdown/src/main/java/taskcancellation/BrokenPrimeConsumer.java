package taskcancellation;

import cn.anger.concurrency.ThreadUtil;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 * 质数消费者
 */
public class BrokenPrimeConsumer {
    private final AtomicInteger count = new AtomicInteger(0);
    private final BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<>(50);
    private final BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
    void consumePrimes() throws InterruptedException {
        producer.start();
        try {
            while (needMorePrimes())
                /*
                 如果 consume 的消费速度慢于 producer 的发布速度
                 如果在 producer 写入队列的这个过程中调用了 producer.cancel() 方法
                 消费者因为 needMorePrimes() 的条件永远无法达成
                 primes.take() 方法阻塞了 BlockingQueue
                 producer 将永远无法停止
                 */
                consume(primes.take());
        } finally {
            producer.cancel();
        }
    }

    boolean needMorePrimes() {
        return count.getAndIncrement() < 100;
    }

    void consume(BigInteger prime) {
        ThreadUtil.sleep(10);
        System.out.printf("%d:%d%n", count.get(), prime);
    }

    public void cancelConsume() {
        producer.cancel();
    }
}

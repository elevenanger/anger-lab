package taskcancellation;

import cn.anger.util.concurrency.ThreadUtil;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
public class FixedPrimeConsumer {
    private final AtomicInteger count = new AtomicInteger(0);
    private final BlockingQueue<BigInteger> primes = new ArrayBlockingQueue<>(50);
    private final FixedPrimeProducer producer = new FixedPrimeProducer(primes);
    void consumePrimes() throws InterruptedException {
        producer.start();
        try {
            while (needMorePrimes())
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

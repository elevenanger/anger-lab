package taskcancellation;

import cn.anger.util.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class BrokenPrimeProducerTest {

    @Test
    void testConsume() throws InterruptedException {
        BrokenPrimeConsumer consumer = new BrokenPrimeConsumer();
        Thread consumeT = new Thread(() -> {
            try {
                consumer.consumePrimes();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        consumeT.start();
        ThreadUtil.sleep(10);
        consumer.cancelConsume();
    }
}
package taskcancellation;

import cn.anger.util.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class FixedPrimeConsumerTest {

    @Test
    void testFixedPrimeConsume() throws InterruptedException {
        FixedPrimeConsumer consumer = new FixedPrimeConsumer();
        Thread t = new Thread(() -> {
            try {
                consumer.consumePrimes();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
        ThreadUtil.sleep(100);
        consumer.cancelConsume();
    }

}
package taskcancellation;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class FixedPrimeProducerTest {

    @Test
    void testCancel() throws InterruptedException {
        FixedPrimeProducer producer = new FixedPrimeProducer(new ArrayBlockingQueue<>(20));
        producer.start();
        producer.cancel();
        producer.join();
    }
}
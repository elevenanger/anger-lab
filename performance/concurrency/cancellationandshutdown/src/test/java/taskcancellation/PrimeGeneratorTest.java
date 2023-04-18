package taskcancellation;

import cn.anger.util.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class PrimeGeneratorTest {

    @Test
    void testCancel() {
        PrimeGenerator generator = new PrimeGenerator();
        generator.run();
        ThreadUtil.sleep(100);
        generator.cancel();
        List<BigInteger> primes = generator.getPrimes();
        assertTrue(primes.size() > 0);
        System.out.println(primes);
    }

}
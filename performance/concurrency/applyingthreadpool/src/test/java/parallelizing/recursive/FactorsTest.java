package parallelizing.recursive;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author : anger
 */
class FactorsTest {

    private final Factors factors = new Factors(BigInteger.valueOf(100));

    @Test
    void sequentialRecursiveTest() {
        Collection<BigInteger> results = new ArrayList<>();
        factors.sequentialRecursive(factors.getChildren(), results);
        System.out.println(results);
    }

    @Test
    void parallelRecursiveTest() throws InterruptedException {
        Collection<BigInteger> results = new ArrayList<>();
        System.out.println(factors.getParallelResults(factors.getChildren()));
    }
}
package cn.anger.servlet;

import cn.anger.util.FactorizationUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author : anger
 */
class SynchronizedCachingFactorizerTest extends FactorizerTest<SynchronizedCachingFactorizer> {

    boolean isConsistency = true;
    @Override
    SynchronizedCachingFactorizer initializeFactorizer() {
        return new SynchronizedCachingFactorizer();
    }

    @Test
    @Override
    void testMultiThreadFactorizer() {
        genericMultiThreadFactorizationChain();
        Assertions.assertTrue(isConsistency);
    }

    @Override
    void requestToFactorizer() {
        super.requestToFactorizer();
        checkConsistency();
    }

    void checkConsistency() {
        BigInteger lastNumber;
        BigInteger[] lastFactors;

        synchronized (servlet) {
            lastNumber = servlet.getLastNumber();
            lastFactors = servlet.getLastFactors();
        }

        if (Objects.nonNull(lastNumber)) {

            final BigInteger[] factors = FactorizationUtil.doFactorization(lastNumber);

            if (!Arrays.equals(lastFactors, factors))
                isConsistency = false;

        }
    }
}
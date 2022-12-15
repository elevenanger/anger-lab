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
class UnsafeCachingFactorizerTest extends FactorizerTest<UnsafeCachingFactorizer> {

    boolean isConsistency = true;

    @Override
    UnsafeCachingFactorizer initializeFactorizer() {
        return new UnsafeCachingFactorizer();
    }

    @Test
    @Override
    void testMultiThreadFactorizer() {
        genericMultiThreadFactorizationChain();
        Assertions.assertFalse(isConsistency);
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
            lastNumber = servlet.getLastNumber().get();
            lastFactors = servlet.getLastFactors().get();
        }

        if (Objects.nonNull(lastNumber)) {

            final BigInteger[] factors = FactorizationUtil.doFactorization(lastNumber);

            if (!Arrays.equals(lastFactors, factors))
                isConsistency = false;

        }
    }
}
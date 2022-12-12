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
class CachedFactorizerTest extends FactorizerTest<CachedFactorizer> {

    private boolean isConcurrency = true;

    @Override
    CachedFactorizer initializeFactorizer() {
        return new CachedFactorizer();
    }

    @Test
    @Override
    void testMultiThreadFactorizer() {
        genericMultiThreadFactorizationChain();
        Assertions.assertTrue(isConcurrency);
    }

    @Override
    void requestToFactorizer() {
        super.requestToFactorizer();
        checkConcurrency();
    }

    void checkConcurrency() {

        BigInteger lastNumber;
        BigInteger[] lastFactors;
        long hits;
        long cacheHits;
        double cacheHitsRate;

        // 使用 synchronized 关键字保护可变状态
        // 将其赋值给局部变量
        // 局部变量不通过线程共享，所以不需要使用 synchronized 关键字
        synchronized (servlet) {
            lastNumber = servlet.getLastNumber();
            lastFactors = servlet.getLastFactors();
            hits = servlet.getHits();
            cacheHits = servlet.getCacheHits();
            cacheHitsRate = servlet.cacheHitsRate();
        }

        if (Objects.nonNull(lastNumber)) {
            if (cacheHitsRate != (double) cacheHits / (double) hits ||
                !Arrays.equals(lastFactors, FactorizationUtil.doFactorization(lastNumber))) {
                isConcurrency = false;
            }
        }
    }

}
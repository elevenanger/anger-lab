package cn.anger.servlet;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class VolatileCacheFactorizerTest extends FactorizerTest<VolatileCacheFactorizer> {
    @Override
    VolatileCacheFactorizer initializeFactorizer() {
        return new VolatileCacheFactorizer();
    }

    @Test
    @Override
    void testMultiThreadFactorizer() {
        genericMultiThreadFactorizationChain();
    }
}
package labutils.servlet;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class EfficientCachedFactorizerTest extends FactorizerTest<EfficientCachedFactorizer> {
    @Override
    EfficientCachedFactorizer initializeFactorizer() {
        return new EfficientCachedFactorizer();
    }

    @Test
    void testMultiThreadFactorizer() {
        genericMultiThreadFactorizationChain();
    }
}
package cn.anger.servlet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class UnsafeCountingFactorizerTest extends FactorizerTest<UnsafeCountingFactorizer> {
    @Override
    UnsafeCountingFactorizer initializeFactorizer() {
        return new UnsafeCountingFactorizer();
    }

    @Test
    void testMultiThreadFactorizer() {
        factorizationChain();
        assertNotEquals((long) THREAD_NUM * LOOP_COUNT, immutableServlet.getCount());
    }
}
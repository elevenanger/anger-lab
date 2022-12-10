package cn.anger.servlet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class UnsafeCountingFactorizerTest extends FactorizerTest<UnsafeCountingFactorizer> {

    final UnsafeCountingFactorizer servlet = new UnsafeCountingFactorizer();

    @Test
    void testMultiFactorization() {
        factorizationChain(servlet);
        assertNotEquals(servlet.getCount(), (long) THREAD_NUM * LOOP_COUNT);
    }
}
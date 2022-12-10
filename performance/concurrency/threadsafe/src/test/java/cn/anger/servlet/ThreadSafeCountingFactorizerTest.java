package cn.anger.servlet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : anger
 */
class ThreadSafeCountingFactorizerTest extends FactorizerTest<ThreadSafeCountingFactorizer> {

    final ThreadSafeCountingFactorizer servlet = new ThreadSafeCountingFactorizer();

    @Test
    void testMultiThreadFactorizer() {
        factorizationChain(servlet);
        assertEquals((long) THREAD_NUM * LOOP_COUNT, servlet.getCount().get());
    }

}
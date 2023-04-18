package labutils.servlet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : anger
 */
class ThreadSafeCountingFactorizerTest extends FactorizerTest<ThreadSafeCountingFactorizer> {

    @Override
    ThreadSafeCountingFactorizer initializeFactorizer() {
        return new ThreadSafeCountingFactorizer();
    }

    @Test
    void testMultiThreadFactorizer() {
        genericMultiThreadFactorizationChain();
        assertEquals((long) THREAD_NUM * LOOP_COUNT, servlet.getCount());
    }

}
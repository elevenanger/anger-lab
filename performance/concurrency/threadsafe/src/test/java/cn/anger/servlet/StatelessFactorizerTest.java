package cn.anger.servlet;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
public class StatelessFactorizerTest extends FactorizerTest<StatelessFactorizer> {
    @Override
    StatelessFactorizer initializeFactorizer() {
        return new StatelessFactorizer();
    }

    @Test
    void testMultiThreadFactorizer() {
        factorizationChain();
    }

}
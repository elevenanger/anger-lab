package cn.anger.servlet;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * @author : anger
 */
public class StatelessFactorizerTest extends FactorizerTest<StatelessFactorizer> {

    // StatelessFactorizer 是线程安全的，只需要创建一个实例即可
    StatelessFactorizer servlet = new StatelessFactorizer();

    @Test
    public void testStatelessFactorizerOnce() {
        toFactorizer.accept(servlet);
    }

    @Test
    void testConcurrentFactorizer() {
        IntStream.range(1_000, 1_010).parallel()
            .boxed()
            .forEach(i -> toFactorizer.accept(servlet));
    }

    @Test
    void testMultiThreadFactorizer() {
        factorizationChain(servlet);
    }

}
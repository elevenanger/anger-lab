package cn.anger.cache;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author : anger
 */
class ComputableTest {

    private final Random random = new Random();
    private final String s = String.valueOf(50);

    void staticCompute(Computable<String, BigInteger> computable) {
        try {
            computable.compute(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void randomCompute(Computable<String, BigInteger> computable) {
        try {
            computable.compute(String.valueOf(random.nextInt(1000)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void computeTest(Computable<String, BigInteger> computable) {
        ConcurrentWorkStream.commonWorkStream(() -> staticCompute(computable)).doWork();
        ConcurrentWorkStream.commonWorkStream(() -> randomCompute(computable)).doWork();
    }

    @Test
    void unCachedComputeTest() {
        System.out.println("UnCachedCompute");
        computeTest(new ExpensiveFunction());
    }

    @Test
    void synchronizedMemorizerComputeTest() {
        System.out.println("SynchronizedMemorizer compute");
        computeTest(new SynchronizedMemorizer<>(new ExpensiveFunction()));
    }

    @Test
    void concurrentMemorizerComputeTest() {
        System.out.println("Concurrent memorizer compute");
        computeTest(new ConcurrentMemorizer<>(new ExpensiveFunction()));
    }

    @Test
    void futureConcurrentMemorizerTest() {
        System.out.println("Future concurrent memorizer test");
        computeTest(new ConcurrentFutureMemorizer<>(new ExpensiveFunction()));
    }

    @Test
    void finalMemorizerTest() {
        System.out.println("Final memorizer test");
        computeTest(new FinalMemorizer<>(new ExpensiveFunction()));
    }
}
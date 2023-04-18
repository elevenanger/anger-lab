package testforcorrectness;

import cn.anger.util.random.Random;
import org.junit.jupiter.api.Assertions;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
class PutTakeTest {
    private static final ExecutorService pool =
        Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> buffer;
    private final int nTrials;
    private final int nPairs;

    PutTakeTest(int capacity, int nPairs, int nTrials) {
        this.buffer = new BoundedBuffer<>(capacity);
        this.nTrials = nTrials;
        this.nPairs = nPairs;
        this.barrier = new CyclicBarrier(nPairs * 2 + 1);
    }

    void test() {
        try {
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new Consumer());
                pool.execute(new Producer());
            }
            barrier.await();
            barrier.await();
            Assertions.assertEquals(putSum.get(), takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            try {
                int seed = Random.getRandom(this.hashCode());
                int sum = 0;
                barrier.await();
                for (int i = nTrials; i > 0; --i) {
                    buffer.put(seed);
                    sum += seed;
                    seed = Random.getRandom(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                barrier.await();
                int sum = 0;
                for (int i = nTrials; i > 0; --i) {
                    sum += buffer.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new PutTakeTest(10, 10, 100_000).test();
        pool.shutdown();
    }
}

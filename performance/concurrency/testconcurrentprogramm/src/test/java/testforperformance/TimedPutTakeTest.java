package testforperformance;

import cn.anger.util.concurrency.ThreadUtil;
import cn.anger.util.random.Random;
import org.junit.jupiter.api.Assertions;
import testforcorrectness.BoundedBuffer;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
class TimedPutTakeTest {
    private static final ExecutorService pool =
        Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);
    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> buffer;
    private final int nTrials;
    private final int nPairs;
    private final BarrierTimer timer;

    TimedPutTakeTest(int capacity, int nPairs, int nTrials) {
        this.buffer = new BoundedBuffer<>(capacity);
        this.nTrials = nTrials;
        this.nPairs = nPairs;
        this.timer = new BarrierTimer();
        this.barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
    }

    void test() {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                pool.execute(new TimedPutTakeTest.Consumer());
                pool.execute(new TimedPutTakeTest.Producer());
            }
            barrier.await();
            barrier.await();
            long nsPerTime = timer.getTime() / (nPairs * (long) nTrials);
            System.out.print("Throughput: " + nsPerTime + "ns/item");
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

    static class BarrierTimer implements Runnable {
        private boolean started;
        private long startTime;
        private long endTime;

        @Override
        public void run() {
            long t = System.nanoTime();
            if (!started) {
                started = true;
                startTime = t;
            } else
                endTime = t;
        }

        public synchronized void clear() {
            started = false;
        }

        public synchronized long getTime() {
            return endTime - startTime;
        }
    }

    public static void main(String[] args) {
        int tpt = 100_000;
        for (int cap = 1; cap <= 1000; cap *= 10) {
            System.out.println("Buffer capacity => " + cap);
            for (int pairs = 1; pairs <= 128; pairs *= 2) {
                // cap buffer 缓冲区大小
                // pairs 生产者消费者线程数量
                // tpt 任务执行次数
                TimedPutTakeTest t = new TimedPutTakeTest(cap, pairs, tpt);
                System.out.print("Pairs: " + pairs + "\t");
                t.test();
                System.out.print("\t");
                ThreadUtil.sleep(1000);
                t.test();
                System.out.println();
                ThreadUtil.sleep(1000);
            }
        }
        pool.shutdown();
    }
}
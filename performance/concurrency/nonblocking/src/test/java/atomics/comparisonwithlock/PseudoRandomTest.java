package atomics.comparisonwithlock;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import cn.anger.util.random.Random;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class PseudoRandomTest {

    private static final int INIT_SEED = 0;

    @Test
    void testReentrantLockPseudoRandom() {
        ReentrantLockPseudoRandom random = new ReentrantLockPseudoRandom(INIT_SEED);

        ConcurrentWorkStream.simpleBenchmark(
            () -> {
                int r = random.nextInt(Random.getRandom());
                if (r == Random.getRandom())
                    System.out.println(r);
            }, "ReentrantLockPseudoRandom"
        );
        /*
        AtomicPseudoRandom :
        WorkLoadConfig{workerAmount=1, workload=1}
        time elapsed => 1.99 ms
        WorkLoadConfig{workerAmount=10, workload=100}
        time elapsed => 1.75 ms
        WorkLoadConfig{workerAmount=20, workload=10000}
        time elapsed => 119.22 ms
         */
    }

    @Test
    void testAtomicPseudoRandom() {
        AtomicPseudoRandom random = new AtomicPseudoRandom(INIT_SEED);

        ConcurrentWorkStream.simpleBenchmark(
            () -> {
                int r = random.nextInt(Random.getRandom());
                if (r == Random.getRandom())
                    System.out.println(r);
            }, "AtomicPseudoRandom"
        );
        /*
        ReentrantLockPseudoRandom :
        WorkLoadConfig{workerAmount=1, workload=1}
        time elapsed => 0.35 ms
        WorkLoadConfig{workerAmount=10, workload=100}
        time elapsed => 5.13 ms
        WorkLoadConfig{workerAmount=20, workload=10000}
        time elapsed => 44.51 ms
         */
    }

}
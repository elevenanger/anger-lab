package atomics.comparisonwithlock;

import cn.anger.util.random.Random;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 * 使用 AtomicInteger 实现伪随机数
 */
public class AtomicPseudoRandom {
    private final AtomicInteger seed;

    public AtomicPseudoRandom(int seed) {
        this.seed = new AtomicInteger(seed);
    }

    public int nextInt(int n) {
        while (true) {
            int s = seed.get();
            int nextSeed = Random.getRandom(s);
            if (seed.compareAndSet(s, nextSeed)) {
                int remainder = s % n;
                return remainder > 0 ? remainder : remainder + n;
            }
        }
    }
}

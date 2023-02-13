package atomics.comparisonwithlock;

import cn.anger.random.Random;

/**
 * @author : anger
 * ThreadLocal 实现伪随机数
 */
public class ThreadLocalPseudoRandom {
    ThreadLocal<Integer> seed = new ThreadLocal<>();
    public ThreadLocalPseudoRandom(int seed) {
        this.seed.set(seed);
    }

    public int getNext(int n) {
        int s = seed.get();
        int nextSeed = Random.getRandom(s);
        seed.set(nextSeed);
        int remainder = s % n;
        return remainder > 0 ? remainder : remainder + n;
    }

}

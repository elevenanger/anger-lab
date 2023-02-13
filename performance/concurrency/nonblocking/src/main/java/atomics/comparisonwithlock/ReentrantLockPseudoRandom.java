package atomics.comparisonwithlock;

import cn.anger.random.Random;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : anger
 * 使用 ReentrantLock 实现的伪随机数
 */
public class ReentrantLockPseudoRandom {
    private final Lock lock = new ReentrantLock(false);
    private int seed;

    public ReentrantLockPseudoRandom(int seed) {
        this.seed = seed;
    }

    public int nextInt(int n) {
        lock.lock();
        try {
            int s = seed;
            seed = Random.getRandom(seed);
            int remainder = s % n;
            return remainder > 0 ? remainder : remainder + n;
        } finally {
            lock.unlock();
        }
    }
}

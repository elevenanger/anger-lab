package fairness;

import cn.anger.random.Random;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : anger
 * 公平锁和非公平锁比较
 */
public class FairAndNonFair {
    private final ReentrantLock lock;

    public FairAndNonFair(ReentrantLock lock) {
        this.lock = lock;
    }

    private void randomWalk() {
        int randomNumber = Random.getRandom();
        if (randomNumber == System.nanoTime())
            System.out.println("bingo");
    }

    public void process() {
        while (true) {
            synchronized (this) {
                try {
                    if (lock.tryLock())
                    {
                        randomWalk();
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

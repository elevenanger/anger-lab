package readwritelocks;

import cn.anger.util.random.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : anger
 * 读写锁
 */
public class ReadAndWrite {
    private final ReadWriteLock lock = new ReentrantReadWriteLock(false);
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();
    private final List<Integer> list = new ArrayList<>();

    public void add(Integer integer) {
        w.lock();
        try {
            list.add(integer);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void sizeCompare() {
        r.lock();
        try {
            if (list.size() == Random.getRandom())
                System.out.println(list.size());
        } finally {
            r.unlock();
        }
    }

    public synchronized void synchronizedAdd(Integer integer) {
        synchronized (this) {
            list.add(integer);
        }
    }

    public synchronized void synchronizedSizeCompare() {
        synchronized (this) {
            if (list.size() == Random.getRandom())
                System.out.println(list.size());
        }
    }
}

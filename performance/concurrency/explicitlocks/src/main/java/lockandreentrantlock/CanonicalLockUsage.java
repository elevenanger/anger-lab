package lockandreentrantlock;

import java.util.concurrent.locks.Lock;

/**
 * @author : anger
 * 显式锁的典型用法
 */
public class CanonicalLockUsage {
    private final Lock lock;
    public Integer count;
    public CanonicalLockUsage(Lock lock) {
        this.lock = lock;
        this.count = 0;
    }

    public void usage() {
        // 获取锁，执行锁定
        lock.lock();
        try {
            // 需要使用锁保护的操作
            count++;
        } finally {
            // finally 块中释放锁
            lock.unlock();
        }
    }
}

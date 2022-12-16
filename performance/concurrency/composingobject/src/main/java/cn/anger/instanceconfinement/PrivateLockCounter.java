package cn.anger.instanceconfinement;

import cn.anger.annotation.GuardedBy;
import cn.anger.annotation.ThreadSafe;

/**
 * @author : anger
 */
@ThreadSafe
public class PrivateLockCounter {
    private final Object lock = new Object();
    @GuardedBy("lock")
    private long count = 0;
    public long takeCount() {
        synchronized (lock) {
            return count++;
        }
    }
}

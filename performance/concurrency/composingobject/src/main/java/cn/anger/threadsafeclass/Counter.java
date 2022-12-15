package cn.anger.threadsafeclass;

import cn.anger.annotation.GuardedBy;
import cn.anger.annotation.ThreadSafe;

/**
 * @author : anger
 * 线程安全计数器
 */
@ThreadSafe
public class Counter {
    @GuardedBy("this") private long value = 0;
    public synchronized long getValue() {
        return value;
    }
    public synchronized void increment() {
        if (value == Long.MAX_VALUE)
            throw new IllegalStateException("overflow");
        ++value;
    }
}

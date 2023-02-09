package explicitconditionobjects;

import cn.anger.annotation.GuardedBy;
import cn.anger.annotation.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : anger
 * 基于 Condition 的有界队列
 */
@ThreadSafe
public class ConditionBoundedBuffer<T> {
    private static final int BUFFER_SIZE = 100_000;
    protected final Lock lock = new ReentrantLock();
    // 条件谓词：notFull(count < item.length)
    private final Condition notFull = lock.newCondition();
    // 条件谓词：notEmpty(count > 0)
    private final Condition notEmpty = lock.newCondition();
    @GuardedBy("lock")
    private final T[] items = (T[]) new Object[BUFFER_SIZE];
    @GuardedBy("lock")
    private int tail;
    @GuardedBy("lock")
    private int head;
    @GuardedBy("lock")
    private int count;

    /**
     * put 元素
     * @param x 元素对象
     */
    public void put(T x) throws InterruptedException {
        lock.lock();
        try {
            // 队列已满，则等待队列未满状态
            while (count == items.length)
                notFull.await();
            items[tail] = x;
            if (++tail == items.length)
                tail = 0;
            ++count;
            // 通知 notEmpty 状态
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取元素
     * @return 返回元素
     */
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            T took = items[head];
            items[head] = null;
            if (++head == items.length)
                head = 0;
            count--;
            notFull.signal();
            return took;
        } finally {
            lock.unlock();
        }
    }
}

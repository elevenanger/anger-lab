package managingstatedependence;

import cn.anger.annotation.GuardedBy;

/**
 * @author : anger
 * 基于数组实现的有界 buffer
 */
public abstract class BaseBoundedBuffer<V> {
    @GuardedBy("this")
    private final V[] buf;
    @GuardedBy("this")
    private int tail;
    @GuardedBy("this")
    private int head;
    @GuardedBy("this")
    private int count;

    protected BaseBoundedBuffer(int capacity) {
        buf = (V[]) new Object[capacity];
    }

    protected synchronized final void doPut(V v) {
        buf[tail] = v;
        if (++tail == buf.length)
            tail = 0;
        ++count;
    }

    protected synchronized final V doTake() {
        V v = buf[head];
        buf[head] = null;
        if (++head == buf.length)
            head = 0;
        --count;
        return v;
    }

    public synchronized boolean isFull() {
        return count == buf.length;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

}

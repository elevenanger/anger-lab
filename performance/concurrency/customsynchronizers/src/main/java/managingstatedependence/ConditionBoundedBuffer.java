package managingstatedependence;

import labutils.annotation.ThreadSafe;

/**
 * @author : anger
 * 基于条件队列实现
 */
@ThreadSafe
public class ConditionBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public ConditionBoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {
        while (isFull())
            wait();
        doPut(v);
        notifyAll();
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty())
            wait();
        V v = doTake();
        notifyAll();
        return v;
    }

}

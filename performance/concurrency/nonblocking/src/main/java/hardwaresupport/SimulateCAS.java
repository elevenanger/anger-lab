package hardwaresupport;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @author : anger
 * 模拟 compare-and-set 以及 compare-and-swap
 */
@ThreadSafe
public class SimulateCAS {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (expectedValue == oldValue)
            value = newValue;
        return oldValue;
    }

    public boolean compareAndSet(int expectedValue, int newValue) {
        return (expectedValue == compareAndSwap(expectedValue, newValue));
    }
}

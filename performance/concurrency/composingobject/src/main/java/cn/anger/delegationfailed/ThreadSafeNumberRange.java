package cn.anger.delegationfailed;

import cn.anger.annotation.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 */
@ThreadSafe
public class ThreadSafeNumberRange {
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);
    private final Object lock = new Object();

    public void setUpper(int i) {
        synchronized (lock) {
            if (i < lower.get())
                System.out.println("can't set upper to " + i + " < lower.");
            else
                upper.set(i);
        }
    }

    public void setLower(int i) {
        if (i > upper.get())
            System.out.println("can't set lower to " + i + " > upper.");
        else
            lower.set(i);
    }

    public boolean isInRange(int i) {
        synchronized (lock) {
            return i <= upper.get() && i >= lower.get();
        }
    }

    public boolean isValid() {
        synchronized (lock){
            return lower.get() <= upper.get();
        }
    }
}

package counter;

import hardwaresupport.SimulateCAS;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @author : anger
 * 使用 CAS 实现非阻塞计数器
 */
@ThreadSafe
public class CasCounter {
    private final SimulateCAS value = new SimulateCAS();

    public int getValue() {
        return value.getValue();
    }

    public int increment() {
        int v;
        do {
            v = value.getValue();
        } while (v != value.compareAndSwap(v, v+1));
        return v + 1;
    }
}

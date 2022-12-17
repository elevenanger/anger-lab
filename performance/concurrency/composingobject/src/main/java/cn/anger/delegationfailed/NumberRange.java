package cn.anger.delegationfailed;

import cn.anger.annotation.NotThreadSafe;
import cn.anger.concurrency.ThreadUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : anger
 * NumberRange 非线程安全
 * setUpper 和 setLower 都是 check-then-act 操作
 * 没有使用充分的锁机制来确保它们的原子性
 */
@NotThreadSafe
public class NumberRange {
    // 不变量 lower <= upper
    private final AtomicInteger upper = new AtomicInteger(0);
    private final AtomicInteger lower = new AtomicInteger(0);

    public void setUpper(int i) {
        if (i < lower.get())
            throw new IllegalArgumentException("can't set upper to " + i + " < lower.");

        upper.set(i);
    }

    public void setLower(int i) {
        if (i > upper.get())
            throw new IllegalArgumentException("can't set lower to " + i + " > upper.");

        ThreadUtil.sleep();
        lower.set(i);
    }

    public boolean isInRange(int i) {
        return (i >= lower.get() && i <= upper.get());
    }
}

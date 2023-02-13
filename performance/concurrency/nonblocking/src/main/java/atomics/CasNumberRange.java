package atomics;

import cn.anger.annotation.Immutable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : anger
 * 使用 AtomicReference 包装 range 不变量 (IntPair)
 * 使用 atomic 变量的 CAS 原子更新不变量确保 range 的不变性
 */
public class CasNumberRange {
    @Immutable
    private static class IntPair {
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    private final AtomicReference<IntPair> values =
        new AtomicReference<>(new IntPair(0, 0));

    public int getLower() {
        return values.get().lower;
    }

    public int getUpper() {
        return values.get().upper;
    }

    public void setLower(int i) {
        while (true) {
            IntPair oldV = values.get();
            if (i > oldV.upper)
                throw new IllegalArgumentException("can't set lower to " + i + " > upper");
            IntPair newV = new IntPair(i, oldV.upper);
            if (values.compareAndSet(oldV, newV))
                return;
        }
    }

    public void setUpper(int i) {
        while (true) {
            IntPair oldV = values.get();
            if (i < oldV.lower)
                throw new IllegalArgumentException("can't set upper to " + i + " < lower");
            IntPair newV = new IntPair(oldV.lower, i);
            if (values.compareAndSet(oldV, newV))
                return;
        }
    }
}

package cn.anger.introduction;

import cn.anger.annotation.NotThreadSafe;

/**
 * @author : anger
 */
@NotThreadSafe
public class UnsafeSequence {
    private int value;

    public int getNext() {
        return value++;
    }
}

package cn.anger.ch01.introduction;

import cn.anger.annotation.ThreadSafe;

/**
 * @author : anger
 */
@ThreadSafe
public class SafeSequence {
    private int value;

    public synchronized int getNext() {
        return value++;
    }
}

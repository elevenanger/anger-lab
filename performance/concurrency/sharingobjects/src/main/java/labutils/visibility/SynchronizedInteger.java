package labutils.visibility;

import labutils.annotation.GuardedBy;
import labutils.annotation.ThreadSafe;

/**
 * @author : anger
 * 对于 get 和 set 方法
 * 都使用 synchronized 关键字
 * 才能避免多线程环境下过时数据的问题
 */
@ThreadSafe
public class SynchronizedInteger {
    @GuardedBy("this") private int value;
    public synchronized int getValue() { return value; }
    public synchronized void setValue(int value) { this.value = value; }
}
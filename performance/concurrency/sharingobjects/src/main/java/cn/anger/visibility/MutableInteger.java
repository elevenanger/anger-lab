package cn.anger.visibility;

import cn.anger.annotation.NotThreadSafe;

/**
 * @author : anger
 * 非线程安全的类
 * 因为 get 和 set 方法分别可以读取和写入变量
 * 在多线程场景下，会存在过时数据的问题
 */
@NotThreadSafe
public class MutableInteger {
    private int value;
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}

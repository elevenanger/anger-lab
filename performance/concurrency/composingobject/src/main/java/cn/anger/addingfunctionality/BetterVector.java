package cn.anger.addingfunctionality;

import cn.anger.annotation.ThreadSafe;

import java.util.Vector;

/**
 * @author : anger
 * 通过继承的形式为已有线程安全的类增加新的功能而不破坏其线程安全
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
    // 新增 check-then-act 原子操作方法
    public synchronized boolean putIfAbsent(E element) {
        boolean absent = !contains(element);
        if(absent)
            add(element);
        return absent;
    }
}

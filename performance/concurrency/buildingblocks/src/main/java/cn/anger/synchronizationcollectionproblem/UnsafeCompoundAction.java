package cn.anger.synchronizationcollectionproblem;

import cn.anger.annotation.NotThreadSafe;
import cn.anger.concurrency.ThreadUtil;

import java.util.Vector;

/**
 * @author : anger
 * 未使用合适的客户端锁的情况下使用线程安全的类执行复合操作
 * 可能会产生意外的结果
 */
@NotThreadSafe
public class UnsafeCompoundAction {
    public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        ThreadUtil.sleep(50);
        return list.get(lastIndex);
    }
    public static void deleteLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
}

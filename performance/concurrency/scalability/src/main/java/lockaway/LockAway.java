package lockaway;

import java.util.List;
import java.util.Vector;

/**
 * @author : anger
 * JVM 会优化掉永远不会发生竞争的锁
 */
public class LockAway {
    public void lockAway() {
        /*
        new Object() 只能通过当前线程进行访问
        永远不会发生竞争
        所以会被 JVM 优化掉
         */
        synchronized (new Object()) {
            System.out.println("lock away");
        }
    }

    public String neverEscape() {
        /*
        descriptions 变量的作用域仅在当前的方法内
        它永远不会被发布到堆上
        不会发生竞争
        正常情况下，需要获取三次 Vector 的锁
        运行时编译器会内联这些调用
        由于它的内部状态永远不会发生逃逸
        所以三次获取锁的操作会被忽略掉
         */
        List<String> descriptions = new Vector<>();
        descriptions.add("never");
        descriptions.add("escape");
        return descriptions.toString();
    }
}

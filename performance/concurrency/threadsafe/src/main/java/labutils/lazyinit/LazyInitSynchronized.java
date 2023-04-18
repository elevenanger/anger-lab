package labutils.lazyinit;

import labutils.annotation.ThreadSafe;

/**
 * @author : anger
 * 使用 synchronized 关键字对于关键点代码进行同步
 */
@ThreadSafe
public class LazyInitSynchronized extends LazyInit<ExpensiveObject> {

    private final Object guard = new Object();

    @Override
    public ExpensiveObject getInstance() {
        // 为了提升性能，使用一个对象对关键代码进行同步,
        // 而不是对整个方法使用 synchronized 关键字
        synchronized (guard) {
            if (expensiveInstance == null)
                expensiveInstance = new ExpensiveObject();
        }
        return expensiveInstance;
    }
}

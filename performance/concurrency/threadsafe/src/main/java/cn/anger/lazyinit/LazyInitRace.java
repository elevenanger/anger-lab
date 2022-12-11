package cn.anger.lazyinit;

import cn.anger.annotation.NotThreadSafe;

/**
 * @author : anger
 *
 * 对资源需求较多的对象进行懒初始化
 */
@NotThreadSafe
public class LazyInitRace extends LazyInit<ExpensiveObject> {

    @Override
    public ExpensiveObject getInstance() {

        if (expensiveInstance == null)
            expensiveInstance = new ExpensiveObject();

        return expensiveInstance;
    }
}

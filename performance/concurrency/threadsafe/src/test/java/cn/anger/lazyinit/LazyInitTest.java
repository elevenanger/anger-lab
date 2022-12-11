package cn.anger.lazyinit;

import org.junit.jupiter.api.TestTemplate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author : anger
 */
abstract class LazyInitTest<T extends LazyInit<?>> {

    T lazy = initializeLazyObject();
    Object expensiveInstance1;
    Object expensiveInstance2;

    abstract T initializeLazyObject();

    void prepareLazyInitRaceCondition() {


        Callable<?> getInstance = () -> lazy.getInstance();

        FutureTask<?> getLazyInstance1 = new FutureTask<>(getInstance);
        FutureTask<?> getLazyInstance2 = new FutureTask<>(getInstance);

        Thread t1 = new Thread(getLazyInstance1);
        Thread t2 = new Thread(getLazyInstance2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();

            expensiveInstance1 = getLazyInstance1.get();
            expensiveInstance2 = getLazyInstance2.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    @TestTemplate
    abstract void doLazyInitRaceConditionTest();

}
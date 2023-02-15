package initialization;

import cn.anger.concurrency.ConcurrentWorkStream;
import cn.anger.concurrency.ThreadUtil;
import jmm.initialization.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author : anger
 * 对象初始化的比较
 */
class InitializationTest {

    Consumer<Supplier<Resource>> sameResource = resourceSupplier -> {
        FutureTask<Resource> task1 = new FutureTask<>(resourceSupplier::get);
        FutureTask<Resource> task2 = new FutureTask<>(resourceSupplier::get);

        ThreadUtil.startAndJoin(
            new Thread(task1),
            new Thread(task2)
        );

        Resource r1 = null;
        Resource r2 = null;

        try {
            r1 = task1.get();
            r2 = task2.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println();
        }

        Assertions.assertNotNull(r1);
        Assertions.assertNotNull(r2);

        Assertions.assertSame(r1, r2);
    };

    @Test
    void SafeLazyInitializationTest() {
        sameResource.accept(SafeLazyInitialization::getInstance);
    }

    @Test
    void eagerInitializationTest() {
        sameResource.accept(EagerInitialization::getResource);
    }

    @Test
    void resourceFactoryTest() {
        sameResource.accept(ResourceFactory::getResource);
    }

    @Test
    void doubleCheckingTest() {
        sameResource.accept(DoubleCheckLocking::getResource);
    }

    @Test
    void compare() {
        ConcurrentWorkStream.simpleBenchmark(ResourceFactory::getResource, "Factory");
        ConcurrentWorkStream.simpleBenchmark(EagerInitialization::getResource, "Eager");
        ConcurrentWorkStream.simpleBenchmark(SafeLazyInitialization::getInstance, "Lazy");
    }

}

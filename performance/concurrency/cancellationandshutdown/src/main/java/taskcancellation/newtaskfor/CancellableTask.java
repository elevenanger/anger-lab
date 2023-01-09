package taskcancellation.newtaskfor;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @author : anger
 * 定义可取消的任务
 */
public interface CancellableTask<T> extends Callable<T> {
    /**
     * cancel 方法
     * 取消任务
     */
    void cancel();

    /**
     * 工厂方法
     * 创建任务
     * @return 可以取消的任务
     */
    RunnableFuture<T> newTask();
}

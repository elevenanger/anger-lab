package executorframework.exectask;

import java.util.concurrent.Executor;

/**
 * @author : anger
 */
public interface ExpensiveTask {
    void perform(Executor executor, int workload);
}

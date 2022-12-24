package executorframework.exectask;

import java.util.concurrent.Executor;

/**
 * @author : anger
 */
public class ExpensiveTaskHandler {
    private Executor executor;
    private ExpensiveTask task;
    private int workload;

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void setTask(ExpensiveTask task) {
        this.task = task;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    void handleTask() {
        task.perform(executor, workload);
    }

}

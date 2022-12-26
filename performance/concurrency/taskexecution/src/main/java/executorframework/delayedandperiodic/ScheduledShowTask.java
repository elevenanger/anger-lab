package executorframework.delayedandperiodic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : anger
 * 使用 ScheduledThreadPool 替代 Timer 执行延时或者需要定期执行的任务
 */
public class ScheduledShowTask {

    public static void main(String[] args) {
        ScheduledExecutorService exec =
            Executors.newScheduledThreadPool(10);
        Runnable showOne = new ShowTask("one");
        Runnable showTwo = new ShowTask("two");
        Runnable abnormalShow = new ShowTask(ShowTask.ABNORMAL);
        exec.scheduleAtFixedRate(showOne, 0, 1000, TimeUnit.MILLISECONDS);
        exec.scheduleAtFixedRate(showTwo, 500, 1000, TimeUnit.MILLISECONDS);
        exec.schedule(abnormalShow, 2000, TimeUnit.MILLISECONDS);
    }
}

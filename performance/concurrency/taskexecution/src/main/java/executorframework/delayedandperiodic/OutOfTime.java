package executorframework.delayedandperiodic;

import cn.anger.util.concurrency.ThreadUtil;

import java.util.Timer;

/**
 * @author : anger
 * 使用 ScheduledThreadPool 来定时或者延时启动任务
 */
public class OutOfTime {

    public static void main(String[] args) {
        // 创建一个 Timer 实例
        Timer timer = new Timer();
        // 定时执行任务
        timer.schedule(new ShowTask("one"), 0, 1000);
        timer.schedule(new ShowTask("two"), 500, 1000);
        ThreadUtil.sleep(2000);
        // 任务抛出异常
        timer.schedule(new ShowTask(ShowTask.ABNORMAL), 0);
    }

}

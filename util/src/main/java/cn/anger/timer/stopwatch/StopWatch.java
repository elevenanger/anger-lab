package cn.anger.timer.stopwatch;

/**
 * @author : anger
 * 简单秒表计时器
 * 适用于单线程顺序执行的代码
 */
public class StopWatch {
    private static StopWatch watch;
    private final TimingTask task;
    private StopWatch() {
        task = new TimingTask();
    }

    public static StopWatch get() {
        if (watch == null)
            watch = new StopWatch();
        return watch;
    }

    /**
     * 启动 StopWatch 设置任务名称和起始时间
     * @param taskName 任务名称
     */
    public void start(String taskName) {
        this.task.startTime = System.nanoTime();
        this.task.taskName = taskName;
    }

    /**
     * 停止 StopWatch
     * 设置终止时间
     */
    public void stop() {
        this.task.endTime = System.nanoTime();
    }

    /**
     * 停止并将任务耗时信息输出到控制台上
     */
    public void stopAndPrint() {
        stop();
        printInfo();
    }

    public void printInfo() {
        System.out.println(task.info());
    }

    private static class TimingTask {
        private String taskName;
        private long startTime;
        private long endTime;
        public String info() {
            return String.format("Task %s time cost => %d ms %n",
                taskName, (endTime - startTime) / 1_000_000);
        }
    }

}

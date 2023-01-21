package thinkingaboutperformance.amdahl;

import java.util.concurrent.BlockingQueue;

/**
 * @author : anger
 * 并非完全可并行化的组件
 * 从队列中取出任务的动作是串行的
 * 结果处理同样可能是串行的
 * Runnable 没有显示提供某种结果处理的机制，那么任务可能存在某种副作用
 * 不然任务就是毫无意义的
 * 任务的副作用，比如写入日志等都可能是串行化的
 * 即使每个线程维护它们产生的结果的数据结构，只有在任务执行完成后才合并
 * 最终的合并动作也是串行的
 */
public class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> tasks;
    public WorkerThread(BlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while(!tasks.isEmpty()) {
            try {
                // 任务从队列中出列是串行的
                Runnable task = tasks.take();
                task.run();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

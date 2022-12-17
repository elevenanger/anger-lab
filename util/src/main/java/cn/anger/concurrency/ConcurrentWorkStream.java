package cn.anger.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
public class ConcurrentWorkStream {
    private ConcurrentWorkStream() {};
    private final List<Thread> workers = new ArrayList<>();
    private Runnable task;
    private int workload;
    private int workerAmount;

    public static ConcurrentWorkStream initialize() {
        return new ConcurrentWorkStream();
    }

    public static ConcurrentWorkStream commonWorkStream(Runnable task) {
        return initialize()
            .withWorkLoadConfig(WorkLoadConfig.Standard)
            .setTask(task);
    }

    public static ConcurrentWorkStream commonWorkStreams(Runnable ... tasks) {
        return Arrays.stream(tasks)
            .map(ConcurrentWorkStream::commonWorkStream)
            .reduce(ConcurrentWorkStream::merge)
            .get();
    }

    public static ConcurrentWorkStream singleWorkStream(Runnable task) {
        return ConcurrentWorkStream.initialize()
            .withWorkLoadConfig(WorkLoadConfig.Single)
            .setTask(task);
    }

    /**
     * 为每个 task 分配一个 worker 执行
     * @param tasks 不同的任务
     * @return Stream
     */
    public static ConcurrentWorkStream workerMatchingTask(Runnable ... tasks) {
        return Arrays.stream(tasks)
            .map(ConcurrentWorkStream::singleWorkStream)
            .reduce(ConcurrentWorkStream::merge)
            .get();
    }

    public ConcurrentWorkStream withWorkLoadConfig(WorkLoadConfig config) {
        this.workload = config.workload;
        this.workerAmount = config.workerAmount;
        return this;
    }

    public void doWork() {
        workerReady();
        ThreadUtil.startAndJoin(workers);
        clean();
    }

    private void clean() {
        this.workers.clear();
        this.task = null;
        this.workload = 1;
    }

    public ConcurrentWorkStream addWorker(Thread worker) {
        workers.add(worker);
        return this;
    }

    public ConcurrentWorkStream addWorkers(Thread ... workers) {
        this.workers.addAll(Arrays.asList(workers));
        return this;
    }

    public ConcurrentWorkStream addWorkers(List<Thread> workers) {
        this.workers.addAll(workers);
        return this;
    }

    public ConcurrentWorkStream merge(ConcurrentWorkStream another) {
        addWorkers(another.getWorkers());
        return this;
    }

    public ConcurrentWorkStream setTask(Runnable task) {
        this.task = task;
        return this;
    }

    private ConcurrentWorkStream setWorkLoad(int workload) {
        this.workload = workload;
        return this;
    }

    private ConcurrentWorkStream setWorkerAmount(Integer workerAmount) {
        this.workerAmount = workerAmount;
        return this;
    }

    private void workerReady() {
        List<Thread> generatedWorkers =
            Stream.generate(() -> new Worker(task).withWorkload(workload))
                .limit(workerAmount)
                .collect(Collectors.toList());
        workers.addAll(generatedWorkers);
    }

    public List<Thread> getWorkers() {
        return workers;
    }

    private static class Worker extends Thread {
        private int workload;
        Worker(Runnable target) {
            super(target);
        }
        Worker withWorkload(int workload) {
            this.workload = workload;
            return this;
        }
        @Override
        public void run() {
            for (int i = 0; i < workload; i++)
                super.run();
        }
    }

    public static class WorkLoadConfig {
        private final int workerAmount;
        private final int workload;
        public static final WorkLoadConfig Single =
            new WorkLoadConfig(1, 1);
        public static final WorkLoadConfig Standard =
            new WorkLoadConfig(10, 100);
        public static final WorkLoadConfig Heavy =
            new WorkLoadConfig(100, 10_000);
        public WorkLoadConfig(int workerAmount, int workload) {
            this.workerAmount = workerAmount;
            this.workload = workload;
        }
    }

}

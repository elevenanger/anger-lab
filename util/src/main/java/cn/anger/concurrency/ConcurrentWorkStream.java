package cn.anger.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : anger
 */
public class ConcurrentWorkStream {
    private ConcurrentWorkStream() {}
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
            .orElseThrow(RuntimeException::new);
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
            .orElseThrow(RuntimeException::new);
    }

    public static ConcurrentWorkStream heavyWorkStream(Runnable task) {
        return ConcurrentWorkStream.initialize()
            .withWorkLoadConfig(WorkLoadConfig.Heavy)
            .setTask(task);
    }

    public ConcurrentWorkStream withWorkLoadConfig(WorkLoadConfig config) {
        setWorkLoad(config.workload);
        setWorkerAmount(config.workerAmount);
        return this;
    }

    private void workerReady() {
        List<Thread> generatedWorkers =
            Stream.generate(() -> new Worker(task).withWorkload(workload))
                .limit(workerAmount)
                .collect(Collectors.toList());
        workers.addAll(generatedWorkers);
    }

    public ConcurrentWorkStream setTask(Runnable task) {
        this.task = task;
        workerReady();
        return this;
    }

    public void doWork() {
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

    private void setWorkLoad(int workload) {
        this.workload = workload;
    }

    private void setWorkerAmount(Integer workerAmount) {
        this.workerAmount = workerAmount;
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

    /**
     * worker 参数配置类
     */
    public static class WorkLoadConfig {
        // worker 数量
        private final int workerAmount;
        // 工作量
        private final int workload;
        // 标准的单线程配置
        public static final WorkLoadConfig Single =
            new WorkLoadConfig(1, 1);
        // 中等线程配置
        public static final WorkLoadConfig Standard =
            new WorkLoadConfig(10, 100);
        // 多线程配置
        public static final WorkLoadConfig Heavy =
            new WorkLoadConfig(20, 10_000);
        public WorkLoadConfig(int workerAmount, int workload) {
            this.workerAmount = workerAmount;
            this.workload = workload;
        }

        @Override
        public String toString() {
            return "WorkLoadConfig{" +
                "workerAmount=" + workerAmount +
                ", workload=" + workload +
                '}';
        }
    }

    public static void simpleBenchmark(final Runnable task, String desc) {
        System.out.println(desc + " : ");
        Semaphore semaphore = new Semaphore(1);
        final Consumer<WorkLoadConfig> consumer =
            config -> {
                try {
                    System.out.println(config);
                    semaphore.acquire();
                    initialize()
                        .withWorkLoadConfig(config)
                        .setTask(task)
                        .doWork();
                    semaphore.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };

        consumer.accept(WorkLoadConfig.Single);
        consumer.accept(WorkLoadConfig.Standard);
        consumer.accept(WorkLoadConfig.Heavy);
    }

}
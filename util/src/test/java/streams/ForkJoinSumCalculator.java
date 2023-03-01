package streams;

import java.util.concurrent.RecursiveTask;

/**
 * author : anger
 * date : 2022/7/12 18:34
 * description : 并行求和
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    public static final long THRESHOLD = 10_000;
    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 任务足够小，不可分割，则顺序执行该任务
        int length = end - start;
        if (length <= THRESHOLD)
            return computeSequentially();

        // 将任务拆分成两个子任务
        ForkJoinSumCalculator leftTask =
            new ForkJoinSumCalculator(numbers, start, start + length / 2);
        // 利用另一个 ForkJoinPool 线程异步执行新创建的子任务
        leftTask.fork();
        ForkJoinSumCalculator rightTask =
            new ForkJoinSumCalculator(numbers, start + length / 2, end);

        // 递归调用本方法，继续拆分子任务
        Long rightResult = rightTask.compute();
        // 读取第一个子任务的结果，如果未完成就继续等待
        Long leftResult = leftTask.join();
        // 该任务的结果是两个子任务结果的组合
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = 0; i < start; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

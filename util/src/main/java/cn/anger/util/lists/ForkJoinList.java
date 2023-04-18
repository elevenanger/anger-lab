package cn.anger.util.lists;


import cn.anger.util.base.BaseResult;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ForkJoinList<T> extends RecursiveTask<List<BaseResult>> {
    private final List<T> originList;
    private final int start;
    private final int end;
    private static final int PART_SIZE = 100;

    private Consumer<List<T>> task;

    public ForkJoinList(List<T> originList, Consumer<List<T>> task) {
        this(originList, 0, originList.size(), task);
        this.task = task;
    }

    public ForkJoinList(List<T> originList, int start, int end, Consumer<List<T>> task) {
        this.originList = originList;
        this.start = start;
        this.end = end;
        this.task = task;
    }

    @Override
    protected List<BaseResult> compute() {
        int len = end - start;
        if (len < PART_SIZE)
            return sequentialCompute();

        ForkJoinList<T> leftList =
            new ForkJoinList<>(originList, start, start + len/2, task);
        leftList.fork();
        ForkJoinList<T> rightList =
            new ForkJoinList<>(originList, start + len/2, end, task);
        List<BaseResult> rightResult = rightList.compute();
        List<BaseResult> leftResult = leftList.join();
        leftResult.addAll(rightResult);
        return leftResult;
    }

    private List<BaseResult> sequentialCompute() {
        task.accept(originList.subList(start, end));
        return originList.subList(start, end).stream()
                .map(v -> new BaseResult(true))
                .collect(Collectors.toList());
    }

}

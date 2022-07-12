package lists;

import base.BaseResult;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

@Slf4j
public class ForkJoinList<T extends Serializable> extends RecursiveTask<List<BaseResult>> {
    private final List<T> originList;
    private final int start;
    private final int end;
    private static final int PART_SIZE = 100;

    public ForkJoinList(List<T> originList) {
        this(originList, 0, originList.size());
    }

    public ForkJoinList(List<T> originList, int start, int end) {
        this.originList = originList;
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<BaseResult> compute() {
        int len = end - start;
        if (len < PART_SIZE)
            return sequentialCompute();

        ForkJoinList<T> leftList = new ForkJoinList<>(originList, start, start + len/2);
        leftList.fork();
        ForkJoinList<T> rightList = new ForkJoinList<>(originList, start + len/2, end);
        List<BaseResult> rightResult = rightList.compute();
        List<BaseResult> leftResult = leftList.join();
        leftResult.addAll(rightResult);
        return leftResult;
    }

    private List<BaseResult> sequentialCompute() {
        return originList.subList(start, end).stream()
                .peek(v -> log.info(v.toString()))
                .map(v -> new BaseResult(true))
                .collect(Collectors.toList());
    }
}

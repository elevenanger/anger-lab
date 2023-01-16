package parallelizing;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author : anger
 * 通过串行和并行的方式分别处理独立的任务
 */
@FunctionalInterface
public interface ElementProcessor<T> {
    default void processSequentially(List<T> elements) {
        elements.forEach(this::process);
    }

    default void processParallel(Executor exec, List<T> elements) {
        elements.forEach(element -> exec.execute(() -> process(element)));
    }

    void process(T element);
}

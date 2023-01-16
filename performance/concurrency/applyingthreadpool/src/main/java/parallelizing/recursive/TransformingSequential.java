package parallelizing.recursive;

import parallelizing.ElementProcessor;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * @author : anger
 * 将串行执行的任务转换成并行执行
 */
public abstract class TransformingSequential implements ElementProcessor<Element> {
    public <T> void sequentialRecursive(List<Node<T>> nodes,
                                        final Collection<T> results) {
        for (final Node<T> node : nodes) {
            results.add(node.compute());
            if (node.getChildren() != null)
                sequentialRecursive(node.getChildren(), results);
        }
    }

    public <T> void parallelRecursive(final Executor exec,
                                      List<Node<T>> nodes,
                                      final Collection<T> results) {
        for (final Node<T> node : nodes) {
            exec.execute(() -> results.add(node.compute()));
            if (node.getChildren() != null)
                parallelRecursive(exec, node.getChildren(), results);
        }
    }

    public <T> Collection<T> getParallelResults(List<Node<T>> nodes)
        throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultsQueue = new ConcurrentLinkedQueue<>();
        parallelRecursive(exec, nodes, resultsQueue);
        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        return resultsQueue;
    }
}

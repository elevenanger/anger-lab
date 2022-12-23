package cn.anger.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author : anger
 * 将 Future 而不是值进行缓存可能会产生缓存污染
 * 因为计算的过程可能会失败或者取消
 * future 尝试去计算结果也同样会发生失败或取消
 * 为了避免这种情况发生
 * 在检查到任务被取消之后将任务从缓存中移除
 */
public class FinalMemorizer<A, V> implements Computable<A, V> {
    final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    final Computable<A, V> computable;

    public FinalMemorizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                FutureTask<V> task = new FutureTask<>(() -> computable.compute(arg));
                // 使用 putIfAbsent() 方法保证原子性
                future = cache.putIfAbsent(arg, task);
                if (future == null) {
                    future = task;
                    task.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                cache.remove(arg, future);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

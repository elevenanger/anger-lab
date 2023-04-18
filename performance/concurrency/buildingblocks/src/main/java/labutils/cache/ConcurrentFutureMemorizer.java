package labutils.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author : anger
 * FutureTask 表示一个当前可能尚未完成的任务
 * FutureTask 在结果可用的时候立即返回执行结果
 * 结果还不可用的时候阻塞直到返回结果
 * 使用 Future 封装 Computable.compute()
 * 每次调用 compute() 的时候检查任务是否已经存在
 * 如果不存在则创建一个 FutureTask 注册到 map 中
 * 如果已存在则调用 future.get() 获取结果
 * 这种方式还存在一个问题
 * 可能会有较小的几率两个线程在计算同一个值
 * 因为判断任务不存在则创建新任务加入 map 中的 put-if-absent 复合操作不是一个原子操作
 */
public class ConcurrentFutureMemorizer<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public ConcurrentFutureMemorizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        // 获取是否已经有 future 存在
        Future<V> future = cache.get(arg);
        // 没有则创建 FutureTask
        // 将 FutureTask 作为 Future 放入缓存
        // 执行计算逻辑
        if (future == null) {
            FutureTask<V> task = new FutureTask<>(() -> computable.compute(arg));
            future = task;
            cache.put(arg, future);
            task.run();
        }
        // 尝试获取 task 的计算结果
        try {
            return future.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

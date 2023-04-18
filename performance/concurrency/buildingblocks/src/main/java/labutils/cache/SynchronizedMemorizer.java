package labutils.cache;

import labutils.annotation.GuardedBy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : anger
 * 缓存包装器
 * 缓存计算结果
 * 封装缓存的过程
 * 通过内在锁来确保线程安全
 * 但是同一时间只有一个线程能够执行计算任务
 * 如果多个线程在排队等待锁
 * 可能比没有缓存的性能还要差
 * 性能较差
 */
public class SynchronizedMemorizer<A, V> implements Computable<A, V>  {
    // HashMap 是非线程安全的
    // 为了确保不会同时有两个线程访问 HashMap
    // 需要使用内在锁来进行同步
    @GuardedBy("this") private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> computable;

    public SynchronizedMemorizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        // 读取缓存中是否已经有记录
        V result = cache.get(arg);
        // 没有则调用 computable.compute() 方法进行计算,将结果缓存
        if (result == null) {
            result = computable.compute(arg);
            cache.put(arg, result);
        }
        // 返回结果
        return result;
    }
}

package cn.anger.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : anger
 * 使用 ConcurrentHashMap 来缓存计算结果
 * 但是由于 Computable 是非线程安全的，
 * 线程之间看不到彼此正在执行的计算
 * 可能会重复计算或者产生其它的安全风险
 */
public class ConcurrentMemorizer<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public ConcurrentMemorizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = computable.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}

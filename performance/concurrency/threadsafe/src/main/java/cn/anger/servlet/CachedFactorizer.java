package cn.anger.servlet;

import cn.anger.annotation.GuardedBy;
import cn.anger.annotation.ThreadSafe;
import cn.anger.util.FactorizationUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * @author : anger
 * 仅使用 synchronized 关键字保护需要确保原子性的复合操作
 */
@ThreadSafe
public class CachedFactorizer extends Factorizer {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;
    @GuardedBy("this") private long hits;
    @GuardedBy("this") private long cacheHits;

    // 所有读写可变状态的地方都应该使用同一个锁进行守护
    public synchronized long getHits() { return hits; }

    public synchronized double cacheHitsRate() {
        return (double) cacheHits / (double) hits;
    }

    public synchronized BigInteger getLastNumber() { return lastNumber; }

    public synchronized BigInteger[] getLastFactors() { return lastFactors; }

    public synchronized long getCacheHits() { return cacheHits; }

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        // 没有使用 synchronized 保护的代码仅使用局部变量
        // 局部变量是基于栈的，不跨线程共享
        // 不需要使用同步机制
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;

        // 第一个 synchronized 用于守护 检查-执行 的操作
        // 以及点击计数和缓存命中计数两个可变状态
        // 获取和释放锁会有一定的开销
        // 尽量不要拆分过多的同步块
        // ++hits 和 ++cacheHits 也放在这个同步块中
        // 为了在性能和线程安全之间取得恰当的平衡
        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (factors == null) {
            factors = FactorizationUtil.doFactorization(i);
            // 第二个 synchronized 用来保护同时更新两个缓存的操作
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }

        encodeIntoResponse(res, factors);

    }
}

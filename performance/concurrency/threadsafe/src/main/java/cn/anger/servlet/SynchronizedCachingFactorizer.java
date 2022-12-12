package cn.anger.servlet;

import cn.anger.annotation.GuardedBy;
import cn.anger.util.FactorizationUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * @author : anger
 * 使用同步机制确保关键点方法的原子性
 */
public class SynchronizedCachingFactorizer extends Factorizer {
    @GuardedBy("this") private BigInteger lastNumber;
    @GuardedBy("this") private BigInteger[] lastFactors;

    public synchronized BigInteger getLastNumber() {
        return lastNumber;
    }

    public synchronized BigInteger[] getLastFactors() {
        return lastFactors;
    }

    /**
     * 处理因式分解的请求
     * 因为 synchronized 关键字使用在方法上，
     * 所以同时只能有一个线程访问调用此方法的对象-即 this 对象本身
     * 这种做法可以确保线程安全
     * 但是会导致很严重的性能问题
     */
    @Override
    public synchronized void service(ServletRequest req, ServletResponse res) {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber))
            encodeIntoResponse(res, lastFactors);
        else {
            BigInteger[] factors = FactorizationUtil.doFactorization(i);
            lastNumber = i;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lastFactors = factors;
            encodeIntoResponse(res, lastFactors);
        }
    }
}

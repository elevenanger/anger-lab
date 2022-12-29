package taskcancellation;

import cn.anger.annotation.GuardedBy;
import cn.anger.annotation.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : anger
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {
    @GuardedBy("this")
    private final List<BigInteger> primes =
        new ArrayList<>();
    // 取消标识
    private volatile boolean cancelled;
    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        // when 每次循环获取下一个质数都检查 cancel 标识是否已经被设置
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    /**
     * how 设置 cancel 标识
     */
    public void cancel() {
        // what
        cancelled = true;
    }

    public synchronized List<BigInteger> getPrimes() {
        return new ArrayList<>(primes);
    }
}

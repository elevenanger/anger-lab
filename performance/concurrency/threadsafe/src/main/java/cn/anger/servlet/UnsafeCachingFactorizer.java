package cn.anger.servlet;

import cn.anger.annotation.NotThreadSafe;
import cn.anger.util.FactorizationUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : anger
 */
@NotThreadSafe
public class UnsafeCachingFactorizer extends Factorizer {
    private final AtomicReference<BigInteger> lastNumber =
        new AtomicReference<>();

    private final AtomicReference<BigInteger[]> lastFactors =
        new AtomicReference<>();

    public AtomicReference<BigInteger> getLastNumber() {
        return lastNumber;
    }

    public AtomicReference<BigInteger[]> getLastFactors() {
        return lastFactors;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        final BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber.get()))
            encodeIntoResponse(res, lastFactors.get());
        else {
            BigInteger [] factors = FactorizationUtil.doFactorization(i);
            lastNumber.set(i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lastFactors.set(factors);
            encodeIntoResponse(res, factors);
        }
    }
}

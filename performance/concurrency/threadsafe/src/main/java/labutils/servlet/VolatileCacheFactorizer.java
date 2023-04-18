package labutils.servlet;

import labutils.annotation.ThreadSafe;
import labutils.threadconfinement.immutable.usingvolatile.OneValueCache;
import labutils.util.FactorizationUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * @author : anger
 */
@ThreadSafe
public class VolatileCacheFactorizer extends Factorizer {
    private volatile OneValueCache cache =
        new OneValueCache(null, new BigInteger[0]);

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = FactorizationUtil.doFactorization(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(res, factors);
    }
}

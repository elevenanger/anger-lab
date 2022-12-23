package cn.anger.servlet;

import cn.anger.cache.FinalMemorizer;
import cn.anger.util.FactorizationUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.math.BigInteger;

/**
 * @author : anger
 */
public class EfficientCachedFactorizer extends Factorizer {
    private final FinalMemorizer<BigInteger, BigInteger[]> cache =
        new FinalMemorizer<>(FactorizationUtil::doFactorization);

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        try {
            BigInteger i = extractFromRequest(req);
            encodeIntoResponse(res, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(res, "factorization interrupted.");
        }
    }
}

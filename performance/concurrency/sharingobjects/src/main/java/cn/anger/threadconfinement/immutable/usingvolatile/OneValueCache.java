package cn.anger.threadconfinement.immutable.usingvolatile;

import cn.anger.annotation.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author : anger
 */
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger number, BigInteger[] factors) {
        this.lastNumber = number;
        this.lastFactors = Arrays.copyOf(factors, factors.length);
    }
    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}

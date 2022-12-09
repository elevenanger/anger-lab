package cn.anger.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : anger
 * 实现因式分解
 */
public class FactorizationUtil {

    private static final BigInteger TWO = new BigInteger("2");

    public static BigInteger[] doFactorization(final BigInteger integer) {

        // 如果待分解的数小于或者等于 1 则直接返回
        if (integer.compareTo(BigInteger.ONE) <= 0 )
            return new BigInteger[]{integer};

        // 因子列表
        final List<BigInteger> factors= new ArrayList<>();
        BigInteger num = integer;
        BigInteger i = TWO;

        while (i.compareTo(num) < 0) {
            if (num.mod(i).equals(BigInteger.ZERO)) {
                factors.add(i);
                num = num.divide(i);
                i = TWO;
                continue;
            }
            i = i.add(BigInteger.ONE);
        }

        factors.add(num);

        System.out.println(factors);

        return factors.toArray(new BigInteger[0]);
    }

}

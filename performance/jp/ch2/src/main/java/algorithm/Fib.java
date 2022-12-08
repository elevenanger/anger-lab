package algorithm;

import java.math.BigInteger;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * author : liuanglin
 * date : 2022/7/27 10:43
 * description : 斐波那契数列
 */
public class Fib {

    public static BigInteger normalWay(int i) {
        if (i == 0 || i == 1) return BigInteger.ONE;
        return normalWay(i - 2).add(normalWay( i - 1));
    }

    public static BigInteger arrayWay(int i) {
        BigInteger [] numbers = new BigInteger[i + 1];
        numbers [0] = BigInteger.ONE;
        numbers [1] = BigInteger.ONE;
        for (int j = 2; j <= i; j++) {
            numbers[j] = numbers[j - 1].add(numbers [j - 2]);
        }
        return numbers[i];
    }

    private BigInteger left = BigInteger.ZERO;

    public BigInteger numbers(int i) {
        Optional<BigInteger> re = Stream.iterate(
            BigInteger.ZERO, integer -> {
                BigInteger result = integer.add(left);
                left = result;
                return result;
            })
            .limit(i)
            .max(BigInteger::compareTo);
        return re.get();
    }

    public static void main(String[] args) {
        System.out.println(arrayWay(10));
    }
}

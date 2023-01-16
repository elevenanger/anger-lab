package parallelizing.recursive;

import cn.anger.concurrency.ThreadUtil;
import cn.anger.util.FactorizationUtil;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author : anger
 */
public class Factors extends TransformingSequential implements Node<BigInteger> {

    private final BigInteger number;

    public Factors(BigInteger number) {
        this.number = number;
    }

    @Override
    public BigInteger compute() {
        ThreadUtil.sleep(10);
        return Arrays.stream(FactorizationUtil.doFactorization(number))
            .reduce(BigInteger::add).orElse(BigInteger.ZERO);
    }

    @Override
    public List<Node<BigInteger>> getChildren() {
        return number.compareTo(BigInteger.valueOf(2)) <= 0 ?
                null:
                Collections.singletonList(new Factors(number.subtract(BigInteger.ONE)));
    }

    @Override
    public void process(Element element) {}
}

package cn.anger.cache;

import cn.anger.concurrency.ThreadUtil;

import java.math.BigInteger;

/**
 * @author : anger
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        ThreadUtil.sleep(1);
        return new BigInteger(arg);
    }
}

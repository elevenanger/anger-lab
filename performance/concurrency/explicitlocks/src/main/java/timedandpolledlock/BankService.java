package timedandpolledlock;

import cn.anger.util.concurrency.ThreadUtil;
import cn.anger.util.random.Random;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author : anger
 */
public class BankService {

    /**
     * 避免死锁
     */
    public boolean transferMoney(BigDecimal amount,
                                 BankAccount fromAccount,
                                 BankAccount toAccount,
                                 long timeout,
                                 TimeUnit unit)
        throws InsufficientBalanceException {
        long delay = Random.getRandomWithLimit(50);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);
        // 不断尝试获取锁
        while (true) {
            if (fromAccount.lock.tryLock()) {
                try {
                    if (toAccount.lock.tryLock()) {
                        try {
                            // 获取到所有需要的锁之后执行业务逻辑
                            fromAccount.credit(amount);
                            toAccount.debit(amount);
                            return true;
                        } finally {
                            toAccount.lock.unlock();
                        }
                    }
                } finally {
                    fromAccount.lock.unlock();
                }
            }
            // 如果已经超时，则返回失败
            if (System.nanoTime() > stopTime)
                return false;
            // 如果没有结束，线程休眠一段时间，继续尝试获取锁，并且防止活锁
            ThreadUtil.sleep(delay);
        }
    }

}

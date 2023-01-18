package deadlock.dynamiclockorder;

import cn.anger.concurrency.ThreadUtil;

import java.math.BigDecimal;

/**
 * @author : anger
 * 转账服务
 */
public class Transfer {
    private Transfer() {}

    /**
     * 转账方法
     * 虽然看起来所有的线程都是按照相同的顺序获取锁
     * 但是实际上获取锁的顺序取决于参数的顺序
     * 还是可能导致死锁
     * @param fromAccount 转出账户
     * @param toAccount 转入账户
     * @param amount 转账金额
     */
    public static void transferMoney(Account fromAccount,
                                     Account toAccount,
                                     BigDecimal amount) {
        // 获取转出账户的锁
        synchronized (fromAccount) {
            ThreadUtil.sleep(100);
            // 获取转入账户的锁
            synchronized (toAccount) {
                fromAccount.credit(amount);
                toAccount.debit(amount);
            }
        }
    }

    public static void main(String[] args) {
        Account a = new BankAccount();
        a.debit(BigDecimal.valueOf(1000));
        Account b = new BankAccount();
        b.debit(BigDecimal.valueOf(1000));
        new Thread(() -> transferMoney(a, b, BigDecimal.valueOf(100))).start();
        new Thread(() -> transferMoney(b, a, BigDecimal.valueOf(200))).start();
    }
}

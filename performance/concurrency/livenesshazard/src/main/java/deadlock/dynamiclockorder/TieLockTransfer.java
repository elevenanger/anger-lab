package deadlock.dynamiclockorder;

import cn.anger.util.concurrency.ThreadUtil;

import java.math.BigDecimal;

/**
 * @author : anger
 * 通过 hashcode 来识别对象
 * 通过 tieLock 来解决 hash 冲突
 * 保证获取锁的顺序
 */
public class TieLockTransfer {

    private TieLockTransfer() {}

    private static final Object tieLock = new Object();

    public static void transfer(final Account fromAccount,
                         final Account toAccount,
                         final BigDecimal amount) {
        class Helper {
            public void transfer() {
                fromAccount.credit(amount);
                toAccount.debit(amount);
            }
        }

        // 获取对象的 hashcode
        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);

        // 通过比较 hashcode 来决定获取锁的顺序
        if (fromHash < toHash) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    new Helper().transfer();
                }
            }
        } else {
            // 存在 hash 冲突则获取 tieLock 的锁
            synchronized (tieLock) {
                synchronized (fromAccount) {
                    synchronized (toAccount) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Account a = new BankAccount();
        a.debit(BigDecimal.valueOf(1000));
        Account b = new BankAccount();
        b.debit(BigDecimal.valueOf(1000));
        new Thread(() -> transfer(a, b, BigDecimal.valueOf(100))).start();
        new Thread(() -> transfer(b, a, BigDecimal.valueOf(200))).start();

        ThreadUtil.sleep(200);

        System.out.println(a.getBalance());
        System.out.println(b.getBalance());
    }
}

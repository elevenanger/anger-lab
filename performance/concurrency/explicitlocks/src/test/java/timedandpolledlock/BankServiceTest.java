package timedandpolledlock;

import cn.anger.util.concurrency.ThreadUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class BankServiceTest {

    @Test
    void testTransfer() {
        BankService service = new BankService();
        BankAccount a1 = new BankAccount();
        BankAccount a2 = new BankAccount();
        a1.debit(new BigDecimal(1000));
        a2.debit(new BigDecimal(1000));
        new Thread(() -> {
            try {
                boolean transferred = service.transferMoney(
                    new BigDecimal(100), a1, a2, 100, TimeUnit.MILLISECONDS);
                assertTrue(transferred);
            } catch (InsufficientBalanceException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            try {
                boolean transferred = service.transferMoney(
                    new BigDecimal(200), a2, a1, 200, TimeUnit.MILLISECONDS);
                assertTrue(transferred);
            } catch (InsufficientBalanceException e) {
                throw new RuntimeException(e);
            }
        }).start();

        ThreadUtil.sleep(100);
        System.out.println(a1.getBalance());
        System.out.println(a2.getBalance());
    }
}
package timedandpolledlock;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : anger
 */
public class BankAccount implements Account {
    private BigDecimal balance = new BigDecimal(0);
    public final Lock lock = new ReentrantLock();

    @Override
    public void credit(BigDecimal amount) throws InsufficientBalanceException {
        if (balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0)
            throw new InsufficientBalanceException();
        balance = balance.subtract(amount);
    }

    @Override
    public void debit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }
}

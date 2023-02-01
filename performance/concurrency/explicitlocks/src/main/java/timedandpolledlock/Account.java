package timedandpolledlock;

import java.math.BigDecimal;

/**
 * @author : anger
 */
public interface Account {
    void credit(BigDecimal amount) throws InsufficientBalanceException;

    void debit(BigDecimal amount);

    BigDecimal getBalance();
}

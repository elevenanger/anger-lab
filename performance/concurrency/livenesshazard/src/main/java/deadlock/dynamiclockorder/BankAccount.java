package deadlock.dynamiclockorder;

import java.math.BigDecimal;

/**
 * @author : anger
 * 银行账户
 */
public class BankAccount implements Account {
    private BigDecimal balance = new BigDecimal(0);

    @Override
    public void debit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException(
                String.format("借记金额 %s 不能小于 0 %n", amount));
        balance = balance.add(amount);
    }

    @Override
    public void credit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException(
                String.format("借记金额 %s 不能小于 0 %n", amount));
        if (balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalStateException("账户可用余额不足");
        balance = balance.subtract(amount);
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }
}

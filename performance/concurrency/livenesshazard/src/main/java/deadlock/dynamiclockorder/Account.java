package deadlock.dynamiclockorder;

import java.math.BigDecimal;

/**
 * @author : anger
 * 账户接口
 */
public interface Account {
    /**
     * 账户借记
     * @param amount 借记金额
     */
    void debit(BigDecimal amount);

    /**
     * 账户贷记
     * @param amount 贷记金额
     */
    void credit(BigDecimal amount);

    /**
     * 查询账户余额
     * @return 账户余额
     */
    BigDecimal getBalance();
}

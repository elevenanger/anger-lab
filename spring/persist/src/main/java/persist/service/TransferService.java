package persist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import persist.model.Account;
import persist.repository.AccountJdbcTemplateRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * author : liuanglin
 * date : 2022/7/25 18:27
 * description : 转账服务
 */
@Service
public class TransferService {

    private final AccountJdbcTemplateRepository repository;

    public TransferService(AccountJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    /*
    @Transactional 开启事务
    如果交易没有抛出 RuntimeException
    事务将会正常提交
    如果有抛出 RuntimeException 事务会被回滚
     */
    @Transactional
    public void transfer(long payerId,
                         long receiverId,
                         BigDecimal amount) {
        Account payer = repository.findById(payerId);
        Account receiver = repository.findById(receiverId);

        BigDecimal payerBalance = payer.getAmount().subtract(amount);
        BigDecimal receiverBalance = receiver.getAmount().add(amount);

        repository.updateAmount(payerId, payerBalance);
        repository.updateAmount(receiverId, receiverBalance);

        /*
        抛出 RuntimeException
        事务回滚
         */
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("金额不能为负数," + amount);
    }

    public List<Account> findAllAccounts() {
        return repository.findAll();
    }

}

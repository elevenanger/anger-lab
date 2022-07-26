package persist.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import persist.model.Account;
import persist.repository.AccountSpringDataRepository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * author : liuanglin
 * date : 2022/7/26 08:25
 * description :
 */
@Slf4j
@Service
public class SpringDataTransferService {

    private final AccountSpringDataRepository repository;

    public SpringDataTransferService(AccountSpringDataRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void transfer(long payerId,
                         long receiverId,
                         BigDecimal amount) {
        Optional<Account> payer = repository.findById(payerId);
        Optional<Account> receiver = repository.findById(receiverId);

        if (payer.isPresent() && receiver.isPresent()) {
            Account payerAccount = payer.get();
            Account receiverAccount = receiver.get();

            if (payerAccount.getAmount().compareTo(amount) < 0)
                throw new RuntimeException("余额不足");

            repository.changeAmount(payerId,
                payerAccount.getAmount().subtract(amount));

            repository.changeAmount(receiverId,
                receiverAccount.getAmount().add(amount));
        }
    }

    public Iterable<Account> findAllAccounts() {
        return repository.findAll();
    }

    public Iterable<Account> findAccountByName(String name) {
        return repository.findAccountByName(name);
    }
}

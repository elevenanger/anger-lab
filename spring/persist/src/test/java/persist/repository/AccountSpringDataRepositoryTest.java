package persist.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import persist.model.Account;

import java.math.BigDecimal;

/**
 * @author : anger
 */
@SpringBootTest
class AccountSpringDataRepositoryTest {

    @Autowired
    private AccountSpringDataRepository repository;

    @Test
    void testSave() {
        for (int i = 0; i < 20; i++) {
            Account account = new Account();
            account.setAmount(new BigDecimal(100));
            account.setName("and");
            repository.save(account);
        }
    }

}
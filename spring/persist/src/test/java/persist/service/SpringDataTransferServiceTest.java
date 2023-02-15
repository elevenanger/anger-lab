package persist.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import persist.model.Account;
import persist.repository.AccountSpringDataRepository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * author : anger
 * date : 2022/7/26 14:12
 */
@SpringBootTest
class SpringDataTransferServiceTest {

    @Test
    @DisplayName("测试正常的交易流程")
    void moneyTransferHappyFlow() {
        /*
        Mockito.mock() 方法创建 mock 对象
        将依赖项进行 mock
        依赖的参数包括
        方法参数
        方法需要使用但不是方法创建的对象实例
         */
        AccountSpringDataRepository repository =
            Mockito.mock(AccountSpringDataRepository.class);
        /*
        创建我们需要进行测试方法所属类的对象
        使用 mock 对象作为实例化参数
        使用这种方法，将依赖项替换为可控的
         */
        SpringDataTransferService transferService =
            new SpringDataTransferService(repository);

        Account payer = new Account();
        payer.setId(1);
        payer.setAmount(new BigDecimal(1000));

        Account receiver = new Account();
        receiver.setId(2);
        receiver.setAmount(new BigDecimal(1000));

        /*
        控制 mock 对象 findByID() 方法返回指定的对象
        如果使用 id 参数调用 findByID() 方法
        返回对应的对象
         */
        BDDMockito.given(repository.findById(payer.getId()))
            .willReturn(Optional.of(payer));

        BDDMockito.given(repository.findById(receiver.getId()))
            .willReturn(Optional.of(receiver));

        transferService.transfer(
            payer.getId(),
            receiver.getId(),
            new BigDecimal(100));

        /*
        校验指定的方法有没有被调用
         */
        BDDMockito.verify(repository)
            .changeAmount(1L, new BigDecimal(900));

        BDDMockito.verify(repository)
            .changeAmount(2L, new BigDecimal(1100));
    }
}
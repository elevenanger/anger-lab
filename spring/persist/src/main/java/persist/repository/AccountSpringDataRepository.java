package persist.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import persist.model.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * author : anger
 * date : 2022/7/26 08:22
 * description : 使用 SpringDataJdbc 的接口实现持久化
 */
@Repository
public interface AccountSpringDataRepository extends CrudRepository<Account, Long> {
    List<Account> findAccountByName(String name);

    /*
    使用 @Modifying 注解标识该方法会修改数据库中的数据
    @Query 注解自定义 SQL 语句 :parameter 从方法中获取参数
     */
    @Modifying
    @Query("UPDATE account SET amount = :amount where id = :id")
    void changeAmount(Long id, BigDecimal amount);
}

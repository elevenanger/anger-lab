package persist.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import persist.model.Account;
import persist.repository.rowmapper.AccountRowMapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * author : liuanglin
 * date : 2022/7/25 18:12
 * description : Account 数据库操作类
 */
@Repository
public class AccountJdbcTemplateRepository {

    private final JdbcTemplate template;

    public AccountJdbcTemplateRepository(JdbcTemplate template) {
        this.template = template;
    }

    public Account findById(long id) {
        String sql = "select * from account where id = ?";
        /*
        使用 RowMapper jdbc 将查询结果映射为对象
         */
        return template.queryForObject(sql, new AccountRowMapper(), id);
    }

    public void updateAmount(long id, BigDecimal amount) {
        String sql = "update account set amount = ? where id = ?";
        template.update(sql, amount, id);
    }

    public List<Account> findAll() {
        String sql = "select * from account";
        return template.query(sql, new AccountRowMapper());
    }
}

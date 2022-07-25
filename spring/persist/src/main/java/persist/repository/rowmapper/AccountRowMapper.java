package persist.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import persist.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author : liuanglin
 * date : 2022/7/25 18:16
 * description : 将数据库执行结果映射为 Account 对象
 */
public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setName(rs.getString("fullname"));
        account.setAmount(rs.getBigDecimal("amount"));
        return account;
    }
}

package persist.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import persist.model.Purchase;

import java.util.List;

/**
 * author : anger
 * date : 2022/7/25 11:16
 * description : purchase 数据库对象
 * 实现 jdbcTemplate 操作数据库
 */
@Repository
public class PurchaseJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public PurchaseJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void storePurchase(Purchase purchase) {
        /*
        SQL 语句字符串
        ? 是占位符，替换为参数值
        id 值为 nextval( 'purchase_id_seq' ) 使用数据库的序列值
         */
        String sql =
            "insert into purchase values (nextval( 'purchase_id_seq' ), ?, ?)";

        jdbcTemplate.update(sql,
            purchase.getProduct(),
            purchase.getPrice());
    }

    public List<Purchase> findAllPurchases() {
        String sql = "select * from purchase";

        /*
        实现 RowMapper 对象用于 JdbcTemplate 将结果集转换成 Purchase 对象
        rs 是结果集
        rowNum 是行号
         */
        RowMapper<Purchase> purchaseRowMapper =
            (rs, rowNum) -> {
                Purchase rowObject = new Purchase();
                rowObject.setId(rs.getInt("id"));
                rowObject.setProduct(rs.getString("product"));
                rowObject.setPrice(rs.getBigDecimal("price"));
                return rowObject;
            };

        return jdbcTemplate.query(sql, purchaseRowMapper);
    }
}

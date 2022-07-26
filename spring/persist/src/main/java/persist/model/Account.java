package persist.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.math.BigDecimal;

/**
 * author : liuanglin
 * date : 2022/7/25 18:10
 * description : 账户类
 */
@Data
public class Account {
    @Id
    private long id;
    @Column("fullname")
    private String name;
    private BigDecimal amount;
}
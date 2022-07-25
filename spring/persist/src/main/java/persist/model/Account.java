package persist.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * author : liuanglin
 * date : 2022/7/25 18:10
 * description : 账户类
 */
@Data
public class Account {
    private long id;
    private String name;
    private BigDecimal amount;
}
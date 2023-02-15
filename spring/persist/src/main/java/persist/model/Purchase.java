package persist.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * author : anger
 * date : 2022/7/25 11:14
 * description : 支付类
 */
@Data
public class Purchase {
    private long id;
    private String product;
    private BigDecimal price;
}

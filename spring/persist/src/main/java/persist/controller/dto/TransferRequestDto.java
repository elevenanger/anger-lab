package persist.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * author : anger
 * date : 2022/7/25 18:44
 */
@Data
public class TransferRequestDto {
    private long payerId;
    private long receiverId;
    private BigDecimal amount;
}

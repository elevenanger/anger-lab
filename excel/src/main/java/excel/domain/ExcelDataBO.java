package excel.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * author : liuanglin
 * date : 2022/7/1 17:39
 * description : Excel 数据对象
 */
@Data
public class ExcelDataBO {
    private String lineNo;
    private String acc;
    private String name;
    private BigDecimal amt;
    private String desc;

}

package excel.data;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * author : anger
 * date : 2022/7/1 17:39
 * description : Excel 数据对象
 */
@Data
@TableName("excel_data")
// 使用 KeySequence 声明 sequence
@KeySequence(value = "test_data_id_seq")
public class ExcelDataPO implements Serializable {
    // 指定 sequence id 类型需要为 Input 类型
    @TableId(type = IdType.INPUT)
    private Long id;
    private String lineNo;
    private String acc;
    @TableField("rec_name")
    private String name;
    @TableField("amount")
    private BigDecimal amt;
    @TableField("summary")
    private String desc;
}

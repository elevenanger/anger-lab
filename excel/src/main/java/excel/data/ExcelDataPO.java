package excel.data;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * author : liuanglin
 * date : 2022/7/1 17:39
 * description : Excel 数据对象
 */
@Data
@Entity
@Table(name = "excel_data")
public class ExcelDataPO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String lineNo;
    private String acc;
    @Column(name = "rec_name")
    private String name;
    @Column(name = "amount")
    private BigDecimal amt;
    @Column(name = "summary")
    private String desc;

}

package domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDate;

/**
 * author : anger
 * date : 2022/7/18 11:25
 * description :
 */
@Data
public class Person {
    @JSONField(name = "date", format = "yyyyMMdd")
    private LocalDate date;
}

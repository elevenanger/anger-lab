package domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * author : liuanglin
 * date : 2022/7/18 11:25
 * description :
 */
@Data
public class Person {
    @JSONField(name = "date", format = "yyyyMMdd")
    private LocalDate date;
    @JSONField(name = "child")
    private List<Child> child;
}

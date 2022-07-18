package domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * author : liuanglin
 * date : 2022/7/18 12:00
 * description :
 */
@Data
public class Child {

    @JSONField(format = "yyyyMMdd")
    private LocalDate childDate;

    @JSONField
    private List<GrandSon> grandSonList;
}

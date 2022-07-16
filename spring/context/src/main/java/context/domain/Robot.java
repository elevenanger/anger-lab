package context.domain;

import org.springframework.stereotype.Component;

/**
 * author : liuanglin
 * date : 2022/7/16 16:52
 * description : 机器人
 */
@Component
public class Robot {

    private String name = "Yang";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

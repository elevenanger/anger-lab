package context.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * author : liuanglin
 * date : 2022/7/15 18:47
 * description : 八哥 - 演示 Spring Component 注解
 * 往 spring context 添加 Bean
 * 使用 @Component 注解将该类声明为 Bean
 * spring 创建该类的实例，并添加到 spring 上下文中
 */
@Component
public class Myna {

    private String name;

    public String getName() {
        return name;
    }

    /*
    使用 @PostConstruct 注解
    spring 会在实例化对象之后调用此方法进行相应操作
     */
    @PostConstruct
    public void init() {
        this.name = "bag";
    }
}

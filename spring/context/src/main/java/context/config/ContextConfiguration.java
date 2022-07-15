package context.config;

import context.domain.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * author : liuanglin
 * date : 2022/7/15 15:00
 * description : 项目的配置类
 * 使用 @Configuration 注解将该类声明为 spring 配置类
 * 配置类是 Spring 应用中一种特殊的类，使得我们可以给 Spring 应用下达特定的指令
 * 使用 @ComponentScan 注解声明查找原型注解的包路径
 */
@Configuration
@ComponentScan("context.domain")
public class ContextConfiguration {

    /**
     * 在配置类中声明方法
     * 返回需要添加到 spring 上下文中的对象实例
     * 使用 @Bean 注解
     * 指示 Spring 在初始化上下文的过程中调用该方法
     * 将返回值添加到上下文中
     * 使用 @Primary 注解
     * 若上下文中存在多个相同类型的 Bean
     * 使用该类型 bean 若未声明 bean name
     * 则默认使用这个 Bean 引用的对象
     * @return 添加到 spring 上下文的对象实例
     */
    @Bean
    @Primary
    Parrot parrot() {
        Parrot parrot = new Parrot();
        parrot.setName("Bla");
        return parrot;
    }

    /*
    可以声明多个相同类型的 Bean
    为了解决歧义的问题
    需要使用 Bean 名称来区分
    spring 默认使用方法名作为 bean 名称
     */
    @Bean
    Parrot parrot2() {
        Parrot parrot = new Parrot();
        parrot.setName("Bala");
        return parrot;
    }

    /*
    可以在 @Bean 注解中显示声明 Bean 名称
    以下三种方式均可
    @Bean("name")
    @Bean(name = "name")
    @Bean(value = "name")
     */
    @Bean(name = "bal")
    Parrot parrot3() {
        Parrot parrot = new Parrot();
        parrot.setName("Bal");
        return parrot;
    }

    @Bean
    String greeting() {
        return "hello";
    }

    @Bean
    Integer year() {
        return 2022;
    }
}

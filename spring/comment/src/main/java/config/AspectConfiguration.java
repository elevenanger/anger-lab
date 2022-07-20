package config;

import aspects.LoggingAspect;
import aspects.SecurityAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import service.SimpleCommentService;

/**
 * author : liuanglin
 * date : 2022/7/19 14:52
 * description : 面向切面编程相关配置
 * 注解 @EnableAspectJAutoProxy 在应用开启切面机制
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"service"})
@EnableAspectJAutoProxy
public class AspectConfiguration {

    /*
    调用特定方法时需要 Spring 执行的逻辑称之为切面
    执行此逻辑的时间称为 建议 （advice）
    执行的方法称为 切点
    触发执行切面的时间称为连接点
    在 Spring 中，连接点都是方法调用
    与依赖注入一样
    切面只能应用于框架管理的对象 （bean）
    声明被切面拦截的方法的 bean 称之为目标对象
    作为切面的目标对象
    从上下文请求该对象时
    Spring 不会直接返回一个对象实例的引用
    取而代之的是 Spring 会返回一个对象调用切面逻辑而不是实际的方法
    这个对象称之为 proxy 对象而不是实际的 bean
    这种方式称之为编织（weaving）
     */
    @Bean
    public SimpleCommentService simpleCommentService() {
        return new SimpleCommentService();
    }

    /*
    通过 @Bean 注解或者原型注解将切面类添加到 Spring 上下文中
     */
    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

    @Bean
    public SecurityAspect securityAspect() {
        return new SecurityAspect();
    }
}

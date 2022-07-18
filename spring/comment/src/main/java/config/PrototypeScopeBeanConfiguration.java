package config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import repositories.DBCommentRepository;

/**
 * author : liuanglin
 * date : 2022/7/18 08:50
 * description : 原型作用域 bean
 * 同一名称的单例 bean 只会在同一个 spring 上下文中创新一次并只会存在一个
 * 但是 Spring 管理原型作用域的 bean 方式则不一样
 * 每次请求一个原型作用域的 bean
 * spring 都会创建一个新的对象实例
 * spring 不直接创建和管理原型作用域 bean 对象实例
 */
@Configuration
@Slf4j
public class PrototypeScopeBeanConfiguration {

    /*
    @Scope 注解可以声明 bean 的作用域
    BeanDefinition.SCOPE_PROTOTYPE 声明为原型作用域
     */
    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public DBCommentRepository repository() {
        log.info("创建 DBCommentRepository 对象");
        return new DBCommentRepository();
    }
}

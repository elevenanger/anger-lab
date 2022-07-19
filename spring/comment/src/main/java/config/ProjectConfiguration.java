package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author : liuanglin
 * date : 2022/7/17 10:15
 * description : 配置类
 * 注解 @ComponentScan 告诉 Spring 应该从哪些路径查找拥有原型注解的类
 */
@Configuration
@ComponentScan(
    basePackages = {"proxies", "service", "repositories", "processor"}
)
public class ProjectConfiguration {}

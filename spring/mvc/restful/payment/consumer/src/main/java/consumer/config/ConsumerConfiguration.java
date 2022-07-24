package consumer.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * author : liuanglin
 * date : 2022/7/24 17:54
 * description : 应用配置类
 * 使用注解 @EnableFeignClients 开启 OpenFeign 功能
 * 并配置 client 路径
 */
@Configuration
@EnableFeignClients(
    basePackages = "consumer.proxy")
public class ConsumerConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*
    在 Spring 上下文中创建 WebClient bean
     */
    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .build();
    }
}

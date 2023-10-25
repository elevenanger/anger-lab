package cn.anger.spring.config.consumer;

import cn.anger.spring.config.SampleConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author : anger
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
            SpringApplication.run(Application.class, args);

        SampleConfigurationProperties properties = context.getBean(SampleConfigurationProperties.class);

        System.out.println(properties.getProjectName());
    }

}

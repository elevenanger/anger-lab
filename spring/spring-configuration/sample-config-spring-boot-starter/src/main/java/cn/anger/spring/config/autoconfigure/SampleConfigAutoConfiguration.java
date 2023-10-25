package cn.anger.spring.config.autoconfigure;

import cn.anger.spring.config.SampleConfigurationProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author : anger
 */
@AutoConfiguration
@EnableConfigurationProperties
public class SampleConfigAutoConfiguration {

    @Bean
    @ConfigurationProperties("sample-config")
    @ConditionalOnClass(SampleConfigurationProperties.class)
    @ConditionalOnMissingBean(SampleConfigurationProperties.class)
    SampleConfigurationProperties sampleConfigurationProperties() {
        return new SampleConfigurationProperties();
    }

}

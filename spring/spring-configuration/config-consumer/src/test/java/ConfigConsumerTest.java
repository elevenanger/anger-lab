import cn.anger.spring.config.SampleConfigurationProperties;
import cn.anger.spring.config.autoconfigure.SampleConfigAutoConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : anger
 */
@SpringBootTest(classes = SampleConfigAutoConfiguration.class)
class ConfigConsumerTest {

    @Autowired
    SampleConfigurationProperties sampleConfigurationProperties;

    @Test
    void consumeSampleConfigProperties() {
        Assertions.assertNotNull(sampleConfigurationProperties);
        System.out.println(sampleConfigurationProperties.getProjectName());
    }

}

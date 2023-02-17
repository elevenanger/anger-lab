package osscli;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Anger
 * created on 2023/2/15
 */
@SpringBootApplication
public class OssCliApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(OssCliApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}

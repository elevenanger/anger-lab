package seqaws;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Anger
 * created on 2023/2/15
 */
@SpringBootApplication
public class SeqAwsApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SeqAwsApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
}

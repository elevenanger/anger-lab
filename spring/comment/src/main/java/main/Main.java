package main;

import config.ProjectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author : anger
 * date : 2022/7/16 20:13
 * description : 主类
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ProjectConfiguration.class);
    }
}

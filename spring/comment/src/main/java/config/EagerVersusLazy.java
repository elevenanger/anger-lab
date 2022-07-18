package config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import repositories.DBCommentRepository;

/**
 * author : liuanglin
 * date : 2022/7/18 08:26
 * description : 比较热初始化和惰性初始化
 */
@Slf4j
@Configuration
public class EagerVersusLazy {

    @Bean("eager")
    public DBCommentRepository eagerDB() {
        log.info("Eager DBCommentRepository Created.");
        return new DBCommentRepository();
    }

    /*
    @Lazy 注解告诉 spring 只有在第一次引用这个 bean 时才创建 bean
     */
    @Bean("lazy")
    @Lazy
    public DBCommentRepository lazyDB() {
        log.info("Lazy DBCommentRepository Created.");
        return new DBCommentRepository();
    }
}

package config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.DBCommentRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/7/18 08:33
 * description : 热初始化与懒初始化的比较
 */
@Slf4j
class EagerVersusLazyTest {

    @Test
    void eagerVersusLazy() {
        log.info("开始加载 spring 上下文");
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(EagerVersusLazy.class);
        log.info("上下文加载完成");

        log.info("引用热初始化的 bean");
        DBCommentRepository eagerRepository =
            context.getBean("eager", DBCommentRepository.class);
        log.info("完成引用热初始化的 bean");
        assertNotNull(eagerRepository);

        log.info("引用懒初始化的 bean ");
        DBCommentRepository lazyRepository =
            context.getBean("lazy", DBCommentRepository.class);
        log.info("完成引用懒初始化的 bean ");
        assertNotNull(lazyRepository);
    }

}
package config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.DBCommentRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : anger
 * date : 2022/7/18 10:15
 * description : 测试原型作用域 bean 特性
 */
class PrototypeScopeBeanConfigurationTest {

    @Test
    void testPrototypeScopeBean() {
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(PrototypeScopeBeanConfiguration.class);

        DBCommentRepository repository1 = context.getBean(DBCommentRepository.class);
        DBCommentRepository repository2 = context.getBean(DBCommentRepository.class);

        /*
        原型作用域的 bean 每次请求 bean
        Spring 使用 bean 的对象类型创建新的实例
         */
        assertNotSame(repository1, repository2);
    }
}
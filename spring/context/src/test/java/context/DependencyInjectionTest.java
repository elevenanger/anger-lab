package context;

import context.config.ContextConfiguration;
import context.domain.Parrot;
import context.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : anger
 * date : 2022/7/16 11:51
 * description : 依赖注入
 */
class DependencyInjectionTest {

    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ContextConfiguration.class);

    @Test
    void testNotWired() {
        Person person = context.getBean("notWired", Person.class);
        System.out.println(person.getName());
        Parrot parrot = context.getBean(Parrot.class);
        System.out.println(parrot.getName());
        assertNull(person.getParrot());
    }

    @Test
    void testDirectWiring() {
        Person person = context.getBean("directWiring", Person.class);
        Person person2 = context.getBean("directWiring2", Person.class);
        System.out.println(person.getName());
        System.out.println(person.getParrot());
        assertNotNull(person.getParrot());
        // bean 已存在时调用 @Bean 方法会直接返回 spring 上下文中 bean 实例，无需新建实例
        assertSame(person.getParrot(), person2.getParrot());
    }

    @Test
    void testParameterWiring() {
        Person person = context.getBean("parameterWiring", Person.class);
        System.out.println(person.getName());
        assertNotNull(person.getParrot());
        System.out.println(person.getParrot());
    }
}

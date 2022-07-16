package context;

import context.config.ContextConfiguration;
import context.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/7/16 15:42
 * description : @Autowired 注解测试
 */
class AutowiredTest {

    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ContextConfiguration.class);

    @Test
    void testAutowiredInField() {
        Person person = context.getBean("person", Person.class);
        assertNotNull(person.getMyna());
        System.out.println(person.getMyna());
    }

    @Test
    void testAutowiredInConstructor() {
        Person person = context.getBean("person", Person.class);
        assertEquals("Bla", person.getParrot().getName());
    }

    @Test
    void testAutowiredInSetter() {
        Person person = context.getBean("person", Person.class);
        assertEquals("Yang", person.getRobot().getName());
    }
}

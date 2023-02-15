package context;

import context.config.ContextConfiguration;
import context.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author : anger
 * date : 2022/7/16 17:17
 * description : 从多个相同类型的 bean 中选择指定的 bean
 */
class ChooseFromMultipleBeanTest {

    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ContextConfiguration.class);

    @Test
    void testChooseByQualifier() {
        Person person = context.getBean("personWithParrot4", Person.class);
        System.out.println(person.getDesc());
        assertEquals("parrot4", person.getParrot().getName());
    }
}

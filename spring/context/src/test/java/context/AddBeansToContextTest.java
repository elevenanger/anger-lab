package context;

import context.config.ContextConfiguration;
import context.domain.Myna;
import context.domain.Parrot;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
/**
 * author : liuanglin
 * date : 2022/7/15 15:24
 * description : 测试将 Bean 添加到 spring 上下文中
 */
class AddBeansToContextTest {

    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ContextConfiguration.class);

    @Test
    void testBeanAnnotationBeans() {
        Parrot parrot = context.getBean("parrot", Parrot.class);
        Parrot parrot2 = context.getBean("parrot2", Parrot.class);
        Parrot parrot3 = context.getBean("bal", Parrot.class);
        Parrot primaryParrot = context.getBean("parrot", Parrot.class);
        assertEquals("Bla", parrot.getName());
        assertEquals("Bla", primaryParrot.getName());
        assertEquals("Bala", parrot2.getName());
        assertEquals("Bal", parrot3.getName());
        assertEquals("hello", context.getBean(String.class));
        assertEquals(2022, context.getBean(Integer.class));
    }

    @Test
    void testComponentAnnotationBean() {
        Myna myna = context.getBean(Myna.class);
        assertNotNull(myna);
        // @PostConstruct 给 name 赋值
        assertEquals("bag", myna.getName());
    }

    @Test
    void testRegisterBean() {
        Parrot parrot = new Parrot();
        parrot.setName("Any");
        Supplier<Parrot> parrotSupplier = () -> parrot;
        context.registerBean("any", Parrot.class, parrotSupplier);
        assertEquals("Any", context.getBean("any", Parrot.class).getName());
    }
}

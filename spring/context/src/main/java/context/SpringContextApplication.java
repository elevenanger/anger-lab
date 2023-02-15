package context;

import context.config.ContextConfiguration;
import context.domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

/**
 * author : anger
 * date : 2022/7/15 14:49
 * description : spring context 学习应用
 */
public class SpringContextApplication {
    public static void main(String[] args) {
        /*
        使用 AnnotationConfigApplicationContext 创建 Spring 上下文实例
        将 bean 添加到 spring 上下文的理由是 spring 只能管理属于它的对象
        将 ContextConfiguration.class 作为参数创建 Spring context
         */
        AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(ContextConfiguration.class);
        // 创建一个 Parrot 实例，未被添加到 spring 上下文中
        Parrot parrot = new Parrot();

        /*
        从 Spring 上下文中获取 Parrot 类型 bean 的对象引用
        确认 ContextConfiguration 中创建的 Parrot 对象已经称为 Spring 上下文中的一部分
         */
        Parrot p = context.getBean("parrot", Parrot.class);
        System.out.println(p.getName());

        parrot.setName("Any");
        /*
        添加指定的对象实例至 spring 上下文
        beanName
        beanClass
        supplier 返回 bean 对象实例
         */
        Supplier<Parrot> parrotSupplier = () -> parrot;
        context.registerBean("any", Parrot.class, parrotSupplier);
    }
}

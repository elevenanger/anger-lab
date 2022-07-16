package context.config;

import context.domain.Parrot;
import context.domain.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * author : liuanglin
 * date : 2022/7/15 15:00
 * description : 项目的配置类
 * 使用 @Configuration 注解将该类声明为 spring 配置类
 * 配置类是 Spring 应用中一种特殊的类，使得我们可以给 Spring 应用下达特定的指令
 * 使用 @ComponentScan 注解声明查找原型注解的包路径
 */
@Configuration
@ComponentScan("context.domain")
public class ContextConfiguration {

    /**
     * 在配置类中声明方法
     * 返回需要添加到 spring 上下文中的对象实例
     * 使用 @Bean 注解
     * 指示 Spring 在初始化上下文的过程中调用该方法
     * 将返回值添加到上下文中
     * 使用 @Primary 注解
     * 若上下文中存在多个相同类型的 Bean
     * 使用该类型 bean 若未声明 bean name
     * 则默认使用这个 Bean 引用的对象
     * @return 添加到 spring 上下文的对象实例
     */
    @Bean
    @Primary
    Parrot parrot() {
        Parrot parrot = new Parrot();
        parrot.setName("Bla");
        return parrot;
    }

    /*
    可以声明多个相同类型的 Bean
    为了解决歧义的问题
    需要使用 Bean 名称来区分
    spring 默认使用方法名作为 bean 名称
     */
    @Bean
    Parrot parrot2() {
        Parrot parrot = new Parrot();
        parrot.setName("Bala");
        return parrot;
    }

    /*
    可以在 @Bean 注解中显示声明 Bean 名称
    以下三种方式均可
    @Bean("name")
    @Bean(name = "name")
    @Bean(value = "name")
     */
    @Bean(name = "bal")
    Parrot parrot3() {
        Parrot parrot = new Parrot();
        parrot.setName("Bal");
        return parrot;
    }

    @Bean
    String greeting() {
        return "hello";
    }

    @Bean
    Integer year() {
        return 2022;
    }

    /*
    在 spring 上下文中存在 Person 和 Parrot bean
    但是它们之间没有建立联系
     */
    @Bean("notWired")
    public Person person() {
        Person person = new Person();
        person.setName("Not Wired");
        return person;
    }

    /*
    手工设置 Person parrot 属性引用的对象为 parrot() 方法返回的 bean 对象
    这种方式称为直接连接
    需要调用定义了所需要依赖的 bean 的方法
    每个 bean 实例在 spring 上下文全局中只有一个

    spring 应用中，首次调用 @Bean 注解的 parrot() 方法时
    spring 会创建一个 Parrot 实例
    在调用 @Bean 注解的方法 personWithParrot() 时
    在方法内调用 parrot() 方法
    这是否意味着又会创建一个 Parrot 对象？
    答案是不会
    当两个注解为 @Bean 的方法之间产生调用关系
    Spring 会认为是需要创建两个 bean 之间的联系
    当 parrot bean 已经存在于 spring 上下文时
    spring 会直接从上下文中获取该实例而不是再调用一次 parrot() 方法
    如果 parrot bean 还未存在于 spring 上下文中
    spring 会调用 parrot() 方法并返回 bean
     */
    @Bean("directWiring")
    public Person personWithParrot() {
        Person person = new Person();
        person.setName("Direct Wiring");
        person.setParrot(parrot());
        return person;
    }
    @Bean("directWiring2")
    public Person personWithParrot2() {
        Person person = new Person();
        person.setName("Direct Wiring2");
        person.setParrot(parrot());
        return person;
    }

    /*
    通过在方法中定义参数
    指示 Spring 从上下文中提供一个 bean
    Spring 会将 parrot bean 注入到这个参数中
    Spring 在调用该方法时将参数设置为特定的值
    解决了这个方法的依赖关系
    依赖注入是一种框架将值设定到指定域或者参数的机制
    DI 是 IoC 原则的一种应用
    IoC 指的是框架在处理过程中控制应用
     */
    @Bean("parameterWiring")
    public Person personWithParrot3(Parrot parrot) {
        Person person = new Person();
        person.setName("Parameter Wiring");
        person.setParrot(parrot);
        return person;
    }

    @Bean()
    public Parrot parrot4() {
        Parrot parrot = new Parrot();
        parrot.setName("parrot4");
        return parrot;
    }

    /*
    从多个相同类型的 Bean 中指定 Bean
    使用 @Qualifier 注解声明 bean 名称
     */
    @Bean
    public Person personWithParrot4(@Qualifier("parrot4") Parrot parrot) {
        Person person = new Person();
        person.setDesc("choose the specific bean by name @Qualifier annotation");
        person.setParrot(parrot);
        return person;
    }
}

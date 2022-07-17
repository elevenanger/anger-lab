package service;

import config.ProjectConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
/**
 * author : liuanglin
 * date : 2022/7/17 20:09
 * description : Spring 中的单例 bean 作用域测试
 * 单例是 Spring bean 默认的作用域
 */
class SingletonScopeTest {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ProjectConfiguration.class);

    @Test
    void singletonTest() {
        /*
        Spring 在加载上下文时创建单例 bean 实例
        并为每个 bean 分配 ID
        之所以称之为单例作用域是因为引用指定的 bean 时总是获取到相同的实例
        常规的单例设计模式
        类管路实例的创建确保该类型在应用中只会创建一个实例
        在 Spring 中，同样的类型可以定义多个 bean 每个 bean 命名不同
        单例意味着命名上的唯一而不是应用中唯一
        从 Spring 上下文中获取单例 bean 时
        如果多次获取的 bean name 相同
        获取到的 bean 总是相同的
         */
        FrameworkCommentService service1 = context.getBean(FrameworkCommentService.class);
        FrameworkCommentService service2 = context.getBean(FrameworkCommentService.class);

        assertSame(service1, service2);
    }

    /*
    spring 上下文中相同的单例作用域的 bean 注入到不同的对象中
    对应的是同一个 bean 实例
    单例 bean 作用域场景下
    app 的多个组件共享同一个对象实例
    单例 bean 必须是不可变的对象
    现实场景下，多个组件可能运行在多个不同线程中
    这些线程共享同一个对象实例
    如果这些线程可以改变这个实例的状态
    将会遇到竞争条件问题
    如果需要可变的单例 bean
    必须自己控制这个 bean 的并发问题
    但是单例的设计不适用于并发场景的
    它们通常用于定义 app 的主干类设计
    并肩职责委派给其它的实现类
     */
    @Test
    void singletonInjectTest() {
        FrameworkCommentService frameworkCommentService =
            context.getBean(FrameworkCommentService.class);
        UserService userService =
            context.getBean(UserService.class);

        assertSame(frameworkCommentService.getRepository(),
                    userService.getRepository());
    }
}

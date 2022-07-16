package context.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * author : liuanglin
 * date : 2022/7/16 11:40
 * description : Person 类
 * 演示依赖注入 Dependency Injection
 * person 拥有 myna 和 parrot
 * 为了实现这一目的，需要两个步骤
 * 1、将 Person Parrot 和 Myna 都作为 bean 添加到 spring 上下文
 * 2、建立它们之间的关系
 */
@Component("person")
public class Person {

    String name;
    private Parrot parrot;

    /*
    @Autowired 注解在实例域上
    Spring 从上下文中取出 Myna bean 的值
    设置为该域的值
     */
    @Autowired
    private Myna myna;

    private Robot robot;

    private String desc;

    public Person() {}

    /*
    @Autowired 注解在构造函数上
    spring 从上下文中获取到 bean 的值
    注入到构造函数的参数中
     */
    @Autowired
    public Person(Parrot parrot) {
        this.parrot = parrot;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Parrot getParrot() {
        return parrot;
    }

    public void setParrot(Parrot parrot) {
        this.parrot = parrot;
    }

    public Myna getMyna() {
        return myna;
    }

    public void setMyna(Myna myna) {
        this.myna = myna;
    }

    public Robot getRobot() {
        return robot;
    }

    /*
    @Autowired 作用于 setter 方法
    Spring 从上下文中获取到 bean
    将其注入到 setter 方法的参数中
    这种方法很少使用
     */
    @Autowired
    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

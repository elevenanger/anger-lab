package starbuzz.component;

import java.math.BigDecimal;

/**
 * @author Anger
 * created on 2022/7/29
 *
 * 装饰器模式：
 * 为对象动态添加额外的职责
 * 装饰器模式为扩展功能提供了一种灵活替代子类的方式
 * 1、装饰器与被装饰的对象有着相同超类
 * 2、可以使用一个或者多个装饰器装饰一个对象
 * 3、鉴于装饰器和被装饰的对象有着相同超类，可以使用装饰器替代原始对象
 * 4、装饰器在其装饰的对象前后添加自己的行为已完成剩余的操作
 * 5、对象可以在任意时间被装饰器装饰，所以可以在运行时使用任意数量的装饰器动态装饰对象
 *
 * 装饰器模式的角色：
 * Component ：
 *    每个 component 都可以单独使用或者被装饰器装饰使用
 * ConcreteComponent：
 *    ConcreteComponent 是动态添加新的行为的对象，它继承了 Component 类
 * Decorator：
 *    每个 decorator 都与一个 component 之间存在 has-a 关系
 *    也就是说 decorator 有一个实例域变量引用一个 component 对象
 *    decorator 与他需要装饰的对象实现相同的接口或者继承于相同的抽象类
 * ConcreteDecorator：
 *    ConcreteDecorator 为他所装饰的对象(compoonent)继承一个实例变量
 *    装饰器可以新增方法
 *    但是新的行为一般在 Component 方法执行的前后执行
 *    装饰器可以继承 component 的状态
 *
 *
 * Beverage 做为装饰器模式中的抽象 Component 类
 */
public abstract class Beverage {
    protected String description= "未知饮料";

    public Size size = Size.TALL;

    public abstract BigDecimal cost();

    public String getDescription() {
        return description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public  Size {TALL, GRANDE, VENTI }
}

package starbuzz.decorator;

import starbuzz.component.Beverage;

import java.math.BigDecimal;

/**
 * @author Anger
 * created on 2022/7/29
 * 调味品类
 * 装饰器模式中的 Decorator
 * Decorator 与被装饰的对象继承相同的抽象类或者实现相同的接口
 *
 * Decorator 与被装饰的 Component 有着相同的类型
 * 这里使用继承是为了类型匹配
 * 而不是用于获取超类的行为
 * 行为是通过装饰器与基础 conponent 对象或者其他装饰器之间的组合实现的
 * 如果依赖于继承，行为会在编译时决定
 * 也就是说只能获得超类提供的行为
 * 使用组合，可以在运行时以任何方式混合以及匹配装饰器
 */
public abstract class CondimentDecorator extends Beverage {
    /*
    实例域
    引用被装饰的对象
    使用被装饰对象的超类
    这样即可以装饰任意类型的 Beverage 对象(component)
     */
    protected Beverage beverage;

    @Override
    public BigDecimal cost() {
        return null;
    }

    /*
    所有的装饰器也都需要实现 getDescription() 方法
     */
    public abstract String getDescription();
}

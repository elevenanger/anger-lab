package starbuzz.concretedecorator;

import starbuzz.component.Beverage;
import starbuzz.decorator.CondimentDecorator;

import java.math.BigDecimal;

/**
 * @author Anger
 * created on 2022/7/29
 * 装饰器模式中的 ConcreteDecorator
 */
public class Milk extends CondimentDecorator {

    /*
    需要使用一个 Beverage 对象(component) 实例化 Milk(装饰器)
    这个 Beverage 对象被装饰的对象
    将需要装饰的对象作为构造函数的参数传入
     */
    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    /*
    首先将 Component 抽象方法的调用委派给 ConcreteComponent 对象本身
    然后在此基础上添加装饰器的行为
    这样即完成了对于被装饰对象的装饰操作
     */
    @Override
    public BigDecimal cost() {
        BigDecimal cost = beverage.cost();
        switch (beverage.getSize()) {
            case TALL: cost = cost.add(BigDecimal.valueOf(0.5));
                break;
            case GRANDE: cost = cost.add(BigDecimal.valueOf(1));
                break;
            case VENTI: cost = cost.add(BigDecimal.valueOf(1.5));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + beverage.getSize());
        }
        return cost;
    }

    /*
    getDescription() 方法与 cost() 方法同理
     */
    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

}

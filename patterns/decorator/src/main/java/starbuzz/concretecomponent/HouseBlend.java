package starbuzz.concretecomponent;

import starbuzz.component.Beverage;

import java.math.BigDecimal;

/**
 * @author Anger
 * created on 2022/7/29
 * 家庭混合风味咖啡
 * 继承 Component 类
 * 装饰器模式中的 ConcreteComponent 对象
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public BigDecimal cost() {
        return BigDecimal.valueOf(1.88);
    }
}

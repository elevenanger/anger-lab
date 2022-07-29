package starbuzz.concretecomponent;

import starbuzz.component.Beverage;

import java.math.BigDecimal;

/**
 * @author Anger
 * created on 2022/7/29
 * 黑烤风味咖啡
 * 装饰器模式中的 ConcreteComponent
 */
public class DarkRoast extends Beverage {

    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public BigDecimal cost() {
        return null;
    }
}

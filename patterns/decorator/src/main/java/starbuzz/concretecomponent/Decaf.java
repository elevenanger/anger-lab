package starbuzz.concretecomponent;

import starbuzz.component.Beverage;

import java.math.BigDecimal;

/**
 * @author Anger
 * created on 2022/7/29
 * 无咖啡因咖啡
 * 装饰器模式中的 ConcreteComponent
 */
public class Decaf extends Beverage {
    public Decaf() {
        description = "Decaf";
    }

    @Override
    public BigDecimal cost() {
        return BigDecimal.valueOf(2.11);
    }
}

package starbuzz.concretecomponent;

import java.math.BigDecimal;

/**
 * @author Anger
 * created on 2022/7/29
 * 意式浓缩风味咖啡
 * 装饰器模式中的 ConcreteComponent
 */
public class Espresso extends DarkRoast{

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public BigDecimal cost() {
        return BigDecimal.valueOf(1.99);
    }
}

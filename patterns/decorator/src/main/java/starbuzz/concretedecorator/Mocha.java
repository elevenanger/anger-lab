package starbuzz.concretedecorator;

import starbuzz.component.Beverage;
import starbuzz.decorator.CondimentDecorator;

import java.math.BigDecimal;

/**
 * @author Anger
 * created on 2022/7/29
 */
public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public BigDecimal cost() {
        return beverage.cost().add(BigDecimal.valueOf(0.5));
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }


}

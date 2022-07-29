import org.junit.jupiter.api.Test;
import starbuzz.component.Beverage;
import starbuzz.concretecomponent.Espresso;
import starbuzz.concretecomponent.HouseBlend;
import starbuzz.concretedecorator.Milk;
import starbuzz.concretedecorator.Mocha;
import starbuzz.concretedecorator.Soy;
import starbuzz.concretedecorator.Whip;

/**
 * @author Anger
 * created on 2022/7/29
 */
class DecoratorPatternTest {

    @Test
    void decoratorTest() {
        Beverage espresso = new Espresso();
        espresso = new Whip(espresso);
        espresso = new Soy(espresso);

        System.out.println(espresso.getDescription() + " " + espresso.cost());

        Beverage house = new HouseBlend();
        house = new Milk(house);
        house = new Soy(house);
        house = new Whip(house);
        house = new Mocha(house);
        System.out.println(house.getDescription() + " " + house.cost());
    }
}

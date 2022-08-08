package template;

import org.junit.jupiter.api.Test;

/**
 * @author Anger
 * created on 2022/8/8
 */
class CaffeineBeverageTest {

    @Test
    void teaTest() {
        CaffeineBeverage tea = new Tea();
        tea.prepareRecipe();
    }

    @Test
    void coffeeTest() {
        CaffeineBeverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
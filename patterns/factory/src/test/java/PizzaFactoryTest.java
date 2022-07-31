import org.junit.jupiter.api.Test;
import pizza.clients.CNStylePizzaStore;
import pizza.clients.PizzaStore;
import pizza.clients.USStylePizzaStore;

/**
 * @author Anger
 * created on 2022/7/31
 */
class PizzaFactoryTest {

    PizzaStore pizzaStore;

    @Test
    void createPizzaTest() {
        pizzaStore = new CNStylePizzaStore();
        pizzaStore.orderPizza("cheese");

        pizzaStore = new USStylePizzaStore();
        pizzaStore.orderPizza("cheese");
    }
}

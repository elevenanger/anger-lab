package pizza.clients;

import pizza.pizzas.CNStyleCheesePizza;
import pizza.pizzas.Pizza;
import pizza.pizzas.USStyleClamPizza;
import pizza.pizzas.USStyleVeggiePizza;

/**
 * @author Anger
 * created on 2022/7/31
 *
 * 纽约风格 Pizza 商店
 */
public class USStylePizzaStore extends PizzaStore{
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "veggie" :
                pizza = new USStyleVeggiePizza();
                break;
            case "clam" :
                pizza = new USStyleClamPizza();
                break;
            case "cheese" :
                pizza = new CNStyleCheesePizza();
                break;
            default:
                throw new IllegalStateException();
        }
        return pizza;
    }
}

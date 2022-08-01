package pizza.store;

import pizza.ingredients.concrete.USIngredientFactory;
import pizza.ingredients.interfaces.PizzaIngredientFactory;
import pizza.pizzas.CheesePizza;
import pizza.pizzas.ClamPizza;
import pizza.pizzas.Pizza;
import pizza.pizzas.VeggiePizza;

/**
 * @author Anger
 * created on 2022/7/31
 *
 * 纽约风格 Pizza 商店
 */
public class USStylePizzaStore extends PizzaStore{

    private final PizzaIngredientFactory factory =
        new USIngredientFactory();

    @Override
    Pizza createPizza(String type) {
        System.out.println("USStylePizzaStore.createPizza");
        Pizza pizza;
        switch (type) {
            case "veggie" :
                pizza = new VeggiePizza(factory);
                break;
            case "clam" :
                pizza = new ClamPizza(factory);
                break;
            case "cheese" :
                pizza = new CheesePizza(factory);
                break;
            default:
                throw new IllegalStateException();
        }
        return pizza;
    }
}

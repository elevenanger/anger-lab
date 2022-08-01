package pizza.store;

import pizza.ingredients.concrete.CNIngredientFactory;
import pizza.ingredients.interfaces.PizzaIngredientFactory;
import pizza.pizzas.*;

/**
 * @author Anger
 * created on 2022/7/31
 *
 * 中国风格 Pizza 商店
 */
public class CNStylePizzaStore extends PizzaStore{

    private final PizzaIngredientFactory factory =
        new CNIngredientFactory();

    /*
    实现具体的 createPizza() 方法
     */
    @Override
    Pizza createPizza(String type) {
        System.out.println("CNStylePizzaStore.createPizza");
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

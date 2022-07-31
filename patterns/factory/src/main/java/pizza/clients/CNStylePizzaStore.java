package pizza.clients;

import pizza.pizzas.*;

/**
 * @author Anger
 * created on 2022/7/31
 *
 * 中国风格 Pizza 商店
 */
public class CNStylePizzaStore extends PizzaStore{

    /*
    实现具体的 createPizza() 方法
     */
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "veggie" :
                pizza = new CNStyleVeggiePizza();
                break;
            case "clam" :
                pizza = new CNStyleClamPizza();
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

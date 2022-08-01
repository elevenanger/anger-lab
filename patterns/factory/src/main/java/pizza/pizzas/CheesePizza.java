package pizza.pizzas;

import pizza.ingredients.interfaces.PizzaIngredientFactory;

/**
 * @author Anger
 * created on 2022/7/31
 *
 * 工厂模式中的具体产品
 *
 * 每个产品都需要继承或者实现 Pizza
 * 并且是具体的 class
 * 只有这样，它才能被工厂方法创建并返回给客户端
 */
public class CheesePizza extends Pizza {

    private final PizzaIngredientFactory pizzaIngredientFactory;

    public CheesePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("CheesePizza.prepare");
        dough = pizzaIngredientFactory.createDough();
        sauce = pizzaIngredientFactory.createSauce();
        cheese = pizzaIngredientFactory.createCheese();
        veggies = pizzaIngredientFactory.createVeggies();
    }
}



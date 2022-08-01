package pizza.pizzas;

import pizza.ingredients.interfaces.PizzaIngredientFactory;

/**
 * @author Anger
 * created on 2022/7/31
 */
public class VeggiePizza extends Pizza {
    private final PizzaIngredientFactory pizzaIngredientFactory;

    public VeggiePizza(PizzaIngredientFactory pizzaIngredientFactory) {
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    @Override
    public void prepare() {
        System.out.println("VeggiePizza.prepare");
        dough = pizzaIngredientFactory.createDough();
        sauce = pizzaIngredientFactory.createSauce();
        cheese = pizzaIngredientFactory.createCheese();
        veggies = pizzaIngredientFactory.createVeggies();
    }
}

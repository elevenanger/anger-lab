package pizza.ingredients.concrete;

import pizza.ingredients.interfaces.*;

/**
 * @author Anger
 * created on 2022/8/1
 */
public class CNIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        System.out.println("CNIngredientFactory.createDough");
        return new HotWaterDough();
    }

    @Override
    public Sauce createSauce() {
        System.out.println("CNIngredientFactory.createSauce");
        return new ChiliSauce();
    }

    @Override
    public Veggies[] createVeggies() {
        System.out.println("CNIngredientFactory.createVeggies");
        return new Veggies[]
            {new Eggplant(), new Mushroom(), new Tomato()};
    }

    @Override
    public Pepperoni createPepperoni() {
        System.out.println("CNIngredientFactory.createPepperoni");
        return new BeefPepperoni();
    }

    @Override
    public Cheese createCheese() {
        System.out.println("CNIngredientFactory.createCheese");
        return new CreamCheese();
    }
}

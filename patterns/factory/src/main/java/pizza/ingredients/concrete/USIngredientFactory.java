package pizza.ingredients.concrete;

import pizza.ingredients.interfaces.*;

/**
 * @author Anger
 * created on 2022/8/1
 *
 * 实现工厂接口
 * 以及每样配料具体的创建方法
 */
public class USIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        System.out.println("USIngredientFactory.createDough");
        return new HotWaterDough();
    }

    @Override
    public Sauce createSauce() {
        System.out.println("USIngredientFactory.createSauce");
        return new SaladSauce();
    }

    @Override
    public Veggies[] createVeggies() {
        System.out.println("USIngredientFactory.createVeggies");
        return new Veggies[]
                {new Eggplant(), new Mushroom(), new Onion()};
    }

    @Override
    public Pepperoni createPepperoni() {
        System.out.println("USIngredientFactory.createPepperoni");
        return new PorkPepperoni();
    }

    @Override
    public Cheese createCheese() {
        System.out.println("USIngredientFactory.createCheese");
        return new FetaCheese();
    }
}

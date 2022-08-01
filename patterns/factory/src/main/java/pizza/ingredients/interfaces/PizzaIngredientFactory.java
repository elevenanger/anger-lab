package pizza.ingredients.interfaces;

/**
 * @author Anger
 * created on 2022/8/1
 *
 * 配料工厂
 * 为每个区域都创建一个配料工厂
 * 所有具体的配料工厂都需要实现这个接口中的创建方法
 * 不同地区的工厂加工当地特色的配料
 *
 * 这个工厂称之为抽象工厂
 * 它可以创建 Pizza 所有需要的配料
 * 抽象工厂给我们接口用于创建产品族
 * 将代码与实际创建产品的工厂解耦
 * 可以实现不同的工厂产生适用于不同上下文的产品
 *
 * 因为代码已经与实际的产品解耦
 * 可以使用不同的工厂来实现不同的行为
 */
public interface PizzaIngredientFactory {

    /*
    每种类型的配料
    都定义一个创建方法
     */
    Dough createDough();

    Sauce createSauce();

    Veggies[] createVeggies();

    Pepperoni createPepperoni();

    Cheese createCheese();
}

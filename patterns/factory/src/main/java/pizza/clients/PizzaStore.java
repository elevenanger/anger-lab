package pizza.clients;

import pizza.pizzas.Pizza;

/**
 * @author Anger
 * created on 2022/7/31
 *
 * Pizza 商店
 * Pizza 工厂
 * 讲工厂生命为 abstract
 * 可以定义不同类型的工厂类具备年不同的对象创建行为
 *
 * 工厂模式的构成：
 * 1、创建者(creator)类
 *    抽象创建者类定义抽象的工厂方法，子类实现工厂方法生产产品
 *    创建者的子类，生产产品的类称之为具体的创建者类
 * 2、产品类：
 *    创建者定义的工厂方法创建的产品类
 *    产品类一般首先定义一个抽象类
 *    并定义具体的子类继承抽象的产品类
 */
public abstract class PizzaStore {

    /*
    将 Pizza 的制作过程也封装在工厂方法中
    这样每个工厂类都有相同的常见过程
     */
    public Pizza orderPizza(String type) {
        Pizza pizza;
        /*
        调用 Pizza 创建的抽象方法
        将创建 Pizza 的工作交给子类实现
        父类不知道也不关心实际创建的 Pizza 类型
        这里实现了解耦
         */
        pizza = createPizza(type);
        processPizza(pizza);
        return pizza;
    }

    /**
     * 将创建对象的方法声明为抽象方法
     * 每个具体的 Pizza 工厂都有不同的创建对象的行为
     * 子类实现具体创建行为
     *
     * @param type Pizza 类型
     * @return Pizza 对象实例
     */
    abstract Pizza createPizza(String type);

    private void processPizza(Pizza pizza) {
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }

}

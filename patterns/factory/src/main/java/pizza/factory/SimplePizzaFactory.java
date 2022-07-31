package pizza.factory;

import pizza.pizzas.USStyleCheesePizza;
import pizza.pizzas.USStyleClamPizza;
import pizza.pizzas.Pizza;
import pizza.pizzas.CNStyleVeggiePizza;

/**
 * @author Anger
 * created on 2022/7/31
 *
 * 工厂模式：
 * 使用 new 关键字实例化一个具体的类
 * 这是一个实现而不是一个接口
 * 将代码绑定到具体的类将会使得代码更加脆弱和不灵活
 * 工程模式处理对象创建的细节
 *
 * 可以在项目中各个地方(clients)调用对象的创建方法
 * 将对象创建封装在一个类中
 * 这个工厂类应该是整个应用中唯一一个可以创建 Pizza 类型对象的地方
 * 当需要对实现进行更改则只需要修改一处地方
 * 并且在调用的地方移除具体类型的对象实例化操作
 *
 * 将工厂方法定义为 static 的方式也比较常见
 * 这种方式的工厂称之为静态工厂
 * 使用静态工厂则不需要实例化工厂对象则可以使用其创建对象
 * 但是它也有不足之处，就是无法继承和改变 create 方法的行为
 *
 */
public class SimplePizzaFactory {

    /**
     * 在工厂类中定义工厂方法
     * 负责 Pizza 对象的创建
     * 根据需要创建的 Pizza 类型创建 Pizza 实例
     * 返回 Pizza 实例
     * 这种非 static 的工厂方法称为简单工厂方法
     * 严格来说，简单工厂方法不是一种设计模式
     * 它更像一种编程习惯
     * @return 具体的 Pizza 实例
     */
    public Pizza createPizza(String type) {
        Pizza pizza;
        switch (type) {
            case "cheese":
                pizza = new USStyleCheesePizza();
                break;
            case "clam":
                pizza = new USStyleClamPizza();
                break;
            case "veggie":
                pizza = new CNStyleVeggiePizza();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return pizza;
    }
}

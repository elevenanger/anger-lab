package pizza.pizzas;

import pizza.ingredients.interfaces.*;

/**
 * @author Anger
 * created on 2022/7/31
 *
 * 工厂模式
 * 需要创建的对象类型
 * 将其定义为抽象类
 * 在抽象类中定义一些有用的方法方便子类重写
 */
public abstract class Pizza {

    String name;

    /*
    披萨具备的配料
     */
    Dough dough;
    Sauce sauce;
    Veggies[] veggies;
    Cheese cheese;
    Pepperoni pepperoni;


    /*
    披萨的制作顺序方法
    准备
    烘焙
    切块
    包装

    将 prepare() 方法定义为抽象方法
    在这个方法中准备制作 Pizza 需要的配料
    这些配料都来源于配料工厂
     */
    public abstract void prepare();

    public void bake() {
        System.out.println(getClass().getSimpleName() + " baked");
    }

    public void cut() {
        System.out.println(getClass().getSimpleName() + " cuts");
    }

    public void box() {
        System.out.println(getClass().getSimpleName() + " boxed");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

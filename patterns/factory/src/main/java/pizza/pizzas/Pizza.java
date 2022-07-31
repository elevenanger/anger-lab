package pizza.pizzas;

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

    /*
    披萨的制作顺序方法
    准备
    烘焙
    切块
    包装
     */
    public void prepare() {
        System.out.println(getClass().getSimpleName() + " prepared");
    }

    public void bake() {
        System.out.println(getClass().getSimpleName() + " baked");
    }

    public void cut() {
        System.out.println(getClass().getSimpleName() + " cuts");
    }

    public void box() {
        System.out.println(getClass().getSimpleName() + " boxed");
    }
}

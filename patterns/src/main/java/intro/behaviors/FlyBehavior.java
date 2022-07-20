package intro.behaviors;

/**
 * @author Anger
 * created on 2022/7/20
 * 飞行的行为
 *
 * 将有变化的部分变成为接口，而不是实现
 * 通过接口定义行为
 * 将具体的行为在特定的实现类中进行实现
 * 而不是在鸭子的子类中直接实现接口
 * 在鸭子类中只需要定义接口类型接口类型
 * 便可以通过传入不同的实现类
 * 来动态改变鸭子的行为
 * 通过这个设计
 * 进一步来说
 * 其他类型对象也可以重用 FlyBehavior
 * 因为这些行为不再是隐藏在 Duck 类中
 * 可以增加新的行为并且不会改变任何已知的行为
 */
public interface FlyBehavior {
    void fly();
}

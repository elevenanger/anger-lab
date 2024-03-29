package adapter;

/**
 * author : anger
 * date : 2022/8/5 21:01
 * description : 适配器模式
 * 将一个接口类型转换成另一个实际能够完成客户端期望操作的接口类型
 * 适配器模式可以让因为接口不兼容无法一起工作的类一起运作
 * 换言之，适配器模式进行了接口的转换和兼容的工作
 * 适配器将客户端与实现接口进行了解耦
 * 接口会随着时间的推移而改变
 * 但是适配器封装了改变
 * 客户端就不需要针对每次接口的改变而做出变化
 * Turkey 适配器
 * 作为 Duck 将请求转换成 Turkey
 * 适配器类：
 * implements Duck
 * 实现适配者的接口，这个是客户端提供的类型
 * 客户端调用 A 类型接口的方法
 * 服务端实现 B 类型接口的方法
 * 适配器实现 A 类型接口，接收 B 类型对象实参
 * 在 A 类型接口实现方法委派给 B 类型实参执行具体的操作
 * 适配器实现
 * 适配器模式中的角色：
 * 1、客户端：
 *    客户端是按照目标接口进行实现的（需要目标接口类型对象）
 *    客户端调用适配器实现的目标接口类型的方法
 * 2、适配器：
 *    适配器实现目标接口的方法
 *    并组合一个被适配类型对象的实例
 *    实现的目标接口方法实际上做出的是被适配者的行为
 *    适配器使用组合的被适配者类型实例
 *    将客户端的请求转换成调用一个或者多个被适配者的方法
 *    委派被适配者执行具体的操作
 *    适配器可以组合一个或者多个被适配者
 * 3、被适配者：
 *    适配器作为目标接口类型接收客户端的请求
 *    将请求转发给被适配者的方法
 *    客户端最终收到的结果是实际是被适配者执行的结果
 *    但是客户端并不知道中间有一个适配器的转换操作
 * 这个例子中
 * 客户端调用 Duck 接口的方法
 * 适配器组合了一个 Turkey 类型对象
 * 适配器将客户端的请求转换成调用 Turkey 的方法
 * 客户端不知道这一层转换的操作
 * 适配器有两种：
 * 1、对象适配器
 * 2、类适配器
 * 这里使用的是你对象适配器
 * 类适配器需要多重继承来实现
 * 适配器需要同时继承目标类和被适配的类
 * 适配器模式与装饰器模式的相同点和区别：
 *    适配器模式和装饰器模式都可以在不修改客户端代码的前提下适配服务端的修改
 *    适配器模式进行接口的转换
 *    装饰器模式不进行接口的装换,但是扩展了接口方法的行为
 */
public class TurkeyAdapter implements Duck {
    private final Turkey turkey;

    /**
     * 在适配者中需要一个对于被适配者类型对象的引用
     * 这里将被适配的类型对象作为适配器构造函数的参数
     * @param turkey 被适配的类型对象
     */
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    /**
     * 实现适配者类型接口的所有的方法
     * 实现中将方法转换成被适配类型的行为
     */
    @Override
    public void fly() {
        turkey.fly();
    }

    @Override
    public void quack() {
        for (int i = 0; i < 5; i++) {
            turkey.gobble();
        }
    }
}

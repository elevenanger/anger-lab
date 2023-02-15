package dynamic;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.UnaryOperator;

/**
 * author : anger
 * date : 2022/8/15 08:39
 * description : 拥有权限的Handler
 * 能够修改 Person 本人的信息
 * 动态代理：
 * 1、创建 InvocationHandler 实现类
 *    实现代理的行为
 *    Java 将会创建实际的代理对象和被代理的对象
 *    编码只需要提供一个Handler当方法调用的时候需要做什么事情
 * 2、编码实现动态代理的创建过程
 *    编码实现代理类对象的实例化
 * 3、使用合适的代理对任意被代理的对象进行包装
 *    这个例子中使用了 Person 对象作为被代理的对象
 *    每个用户访问的 Person 对象可能是本人也可能不是本人
 *    所以有两个 InvocationHandler，分别为
 *    OwnerInvocationHandler 控制本人场景下对于 Person 对象的访问
 *    NonOwnerInvocationHandler 控制非本人场景下对于 Person 对象的访问
 * 动态代理中代理对象在运行时按需创建
 * 实现 InvocationHandler 接口
 */
@Slf4j
public class OwnerInvocationHandler implements InvocationHandler {
    Person person;
    /**
     * 将真实的主体作为 InvocationHandler 的参数
     * @param person 被代理的 Person 真实的主体
     */
    public OwnerInvocationHandler(Person person) {
        this.person = person;
    }

    /**
     * invoke 方法
     * 处理对于真实主体方法进行调用的控制逻辑
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        log.info("调用方法 {}", method.getName());
        try {
            if (method.getName().startsWith("get"))
                return method.invoke(person, args);
            else if (method.getName().equals("setGeekRating"))
                throw new IllegalAccessException();
            else if (method.getName().startsWith("set"))
                return method.invoke(person,args);
        }  catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 创建 Proxy 动态代理对象
     * 接收真实主体作为参数
     * @param person 实际主体对象
     * @return 代理实际主体的代理对象
     */
    public static Person getOwnerProxy(Person person) {
        /*
        调用 Proxy.newProxyInstance() 创建 proxy
        主体对象类的 ClassLoader 、实现的 Interface 、InvocationHandler 作为参数
        将真实主体对象作为参数传递给 InvocationHandler 构造函数
         */
        return (Person) Proxy.newProxyInstance(
            person.getClass().getClassLoader(),
            person.getClass().getInterfaces(),
            new OwnerInvocationHandler(person));
    }

    /**
     * 使用一元函数创建 Proxy
     */
    public static final UnaryOperator<Person> genOwnerProxyFunc =
        realPerson ->
            (Person) Proxy.newProxyInstance(
                realPerson.getClass().getClassLoader(),
                realPerson.getClass().getInterfaces(),
                new OwnerInvocationHandler(realPerson));
}

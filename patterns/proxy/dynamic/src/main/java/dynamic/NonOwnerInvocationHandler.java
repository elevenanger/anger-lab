package dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.UnaryOperator;

/**
 * author : anger
 * date : 2022/8/15 08:39
 * description : 不具备权限的 Handler
 * 只能给用户进行打分，不能访问其它方法
 */
public class NonOwnerInvocationHandler implements InvocationHandler {
    private Person person;

    public NonOwnerInvocationHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            if (method.getName().equals("setGeekRating"))
                method.invoke(person, args);
            else
                throw new IllegalAccessException();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Person getNonOwnerProxy(Person person) {
        return (Person) Proxy.newProxyInstance(
            person.getClass().getClassLoader(),
            person.getClass().getInterfaces(),
            new NonOwnerInvocationHandler(person)
        );
    }

    public static final UnaryOperator<Person> genNonOwnerProxyFunc =
        realPerson ->
            (Person) Proxy.newProxyInstance(
                realPerson.getClass().getClassLoader(),
                realPerson.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(realPerson));
}

package dynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static dynamic.OwnerInvocationHandler.genOwnerProxyFunc;
import static dynamic.NonOwnerInvocationHandler.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * author : anger
 * date : 2022/8/15 11:34
 * description : 动态代理测试
 */
@Slf4j
class PersonTest {

    private static Person zhang;
    private static Person li;

    private static Person zhangOwner;
    private static Person liOwner;

    private static Person zhangOther;
    private static Person liOther;

    @BeforeAll
    static void prepare() {
        log.info("实例化主体对象");
        zhang = PersonImpl.newPersonInstance.apply("Zhang San", "male");
        li = PersonImpl.newPersonInstance.apply("Li Si", "male");

        log.info("生成本人代理");
        zhangOwner = genOwnerProxyFunc.apply(zhang);
        liOwner = genOwnerProxyFunc.apply(li);

        log.info("生成非本人代理");
        zhangOther = genNonOwnerProxyFunc.apply(zhang);
        liOther = genNonOwnerProxyFunc.apply(li);
    }

    @Test
    void invocationOwnerMethod() {
        log.info("{} 调用本人有权调用的方法前", zhang);
        zhangOwner.setName("Zhang San Xin");
        log.info("{} 调用本人有权调用的方法后", zhang);

        log.info("{} 调用本人有权调用的方法前", li);
        liOwner.setName("Li Si Xin");
        log.info("{} 调用本人有权调用的方法后", li);
    }

    @Test
    void invocationNonOwnerMethod() {
        log.info("其它人调用 {} setGeekRating() 方法前", zhang);
        zhangOther.setGeekRating(7);
        zhangOther.setGeekRating(8);
        zhangOther.setGeekRating(5);
        log.info("其它人调用 {} setGeekRating() 方法后", zhang);

        log.info("其它人调用 {} setGeekRating() 方法前", li);
        liOther.setGeekRating(8);
        liOther.setGeekRating(6);
        liOther.setGeekRating(9);
        log.info("其它人调用 {} setGeekRating() 方法前", li);
    }

    @Test
    void invocationForbidMethod() {
        log.info("{} 调用无权限调用的方法", zhang);
        assertThrows(RuntimeException.class, () -> zhangOwner.setGeekRating(10));
    }

    @Test
    void isProxyTest() {
        log.info("本体类：{}，代理类：{}",
                    zhang.getClass(),
                    zhangOther.getClass());
        // Proxy.isProxyClass() 方法可以判断一个对象是不是代理类对象
        assertTrue(Proxy.isProxyClass(zhangOther.getClass()));
    }
}
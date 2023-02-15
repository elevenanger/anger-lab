package jmm.initialization;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @author : anger
 * 通过 synchronized 同步初始化实例访问操作
 */
@ThreadSafe
public class SafeLazyInitialization {
    private static Resource resource;

    public static synchronized Resource getInstance() {
        if (resource == null)
            resource = new Resource();
        return resource;
    }
}

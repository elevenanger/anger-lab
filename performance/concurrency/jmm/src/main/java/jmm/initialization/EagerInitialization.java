package jmm.initialization;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @author : anger
 * 通过 static 字段实现对象的安全发布
 */
@ThreadSafe
public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getResource() {
        return resource;
    }
}

package jmm.initialization;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * @author : anger
 * DCL 实现懒加载
 */
@NotThreadSafe
public class DoubleCheckLocking {
    private static Resource resource;

    public static Resource getResource() {
        if (resource == null) {
            synchronized (DoubleCheckLocking.class) {
                if (resource == null)
                    resource = new Resource();
            }
        }
        return resource;
    }
}

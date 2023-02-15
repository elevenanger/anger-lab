package jmm.initialization;

/**
 * @author : anger
 * 通过一个内部类 holder 实现懒加载
 */
public class ResourceFactory {
    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    public static Resource getResource() {
        return ResourceHolder.resource;
    }
}

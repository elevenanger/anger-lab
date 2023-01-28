package narrowlock;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author : anger
 */
@ThreadSafe
public class AttributeStore {
    @GuardedBy("this")
    private final Map<String, String> attributes = new HashMap<>();

    public synchronized void setUserLocationAttributes(String name, String location) {
        String key = "user." + name + ".location";
        attributes.put(key, location);
    }

    /**
     * 通过用户名匹配位置
     * @param name 用户名
     * @param regexp 正则表达式
     * @return 匹配结果
     * 整个方法都使用了 synchronized 关键字
     * 但是其实只有在 Map.get() 方法才会获取锁
     */
    public synchronized boolean userLocationMatches(String name, String regexp) {
        String key = "user." + name + ".location";
        String location = attributes.get(key);
        if (location == null)
            return false;
        else
            return Pattern.matches(regexp, location);
    }
}

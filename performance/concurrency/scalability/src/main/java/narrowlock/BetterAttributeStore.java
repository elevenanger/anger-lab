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
public class BetterAttributeStore {
    @GuardedBy("this")
    private final Map<String, String> attributes = new HashMap<>();

    public void setUserLocationAttributes(String name, String location) {
        String key = "user." + name + ".location";
        synchronized (this) {
            attributes.put(key, location);
        }
    }

    public boolean userLocationMatches(String name, String regexp) {
        String key = "user." + name + ".location";
        String location;
        synchronized (this) {
            location = attributes.get(key);
        }
        if (location == null)
            return false;
        else
            return Pattern.matches(regexp, location);
    }
}

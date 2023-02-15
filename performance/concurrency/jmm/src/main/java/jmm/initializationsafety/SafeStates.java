package jmm.initializationsafety;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : anger
 * 正确构建对象中的 final 字段对于所有线程可见
 */
@ThreadSafe
public class SafeStates {
    private final Map<String, String> states;

    public SafeStates() {
        states = new HashMap<>();
        states.put("an1", "ger1");
        states.put("an2", "ger2");
        states.put("an3", "ger3");
        states.put("an4", "ger4");
        states.put("an5", "ger5");
    }

    public String getState(String s) {
        return states.get(s);
    }
}

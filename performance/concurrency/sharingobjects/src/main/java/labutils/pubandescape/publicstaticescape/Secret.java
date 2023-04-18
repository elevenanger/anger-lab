package labutils.pubandescape.publicstaticescape;

import labutils.annotation.NotThreadSafe;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : anger
 * 最明显的对象发布方式
 * 将对象的引用存储在一个 public static 变量中
 * 其它的线程可以看到这些数据
 */
@NotThreadSafe
public class Secret {
    public static Set<Secret> knownSecret;
    /**
     * 每次调用 initialize 方法都会对 knownSecret 变量重新赋值
     * 这种对象发布的方式是非线程安全的
     */
    public void initialize() {
        knownSecret = new HashSet<>();
    }
}

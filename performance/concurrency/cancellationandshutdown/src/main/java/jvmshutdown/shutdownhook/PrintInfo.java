package jvmshutdown.shutdownhook;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * @author : anger
 * 注册 shutdownHook
 */
public class PrintInfo {
    public void start() {
        // 注册 shutdownHook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
                System.out.println(runtimeMXBean.getName() + " exit.");
        }));
    }
}

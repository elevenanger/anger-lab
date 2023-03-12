package cn.anger.dump;

import com.sun.management.HotSpotDiagnosticMXBean;

import javax.management.MBeanServer;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author : anger
 */
public class DumpUtil {
    private DumpUtil() {}

    public static final String COMMON_DIR = "/Users/" + System.getProperty("user.name") + "/data/";

    public static void dumpHeap(final String fileName, boolean live) throws IOException {
        final String filePath = COMMON_DIR + "dump/" + fileName;
        Files.deleteIfExists(Paths.get(filePath));
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(
            server, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
        mxBean.dumpHeap(filePath, live);
    }
}

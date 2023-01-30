package cn.anger.dump;

import cn.anger.file.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author : anger
 */
class DumpUtilTest {

    @Test
    void testHeapDump() throws IOException {
        DumpUtil.dumpHeap("./dump1.hprof", true);
        DumpUtil.dumpHeap("./dump2.hprof", false);
        System.out.println(FileUtil.getFileSizeMegaBytes("./dump1.hprof"));
        System.out.println(FileUtil.getFileSizeMegaBytes("./dump2.hprof"));
    }

}
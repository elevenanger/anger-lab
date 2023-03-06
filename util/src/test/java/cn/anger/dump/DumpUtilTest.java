package cn.anger.dump;

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
    }

}
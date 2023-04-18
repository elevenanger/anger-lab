package managingstatedependence;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class GrumpyBoundedBufferTest {


    GrumpyBoundedBuffer.ExampleUsage exampleUsage = new GrumpyBoundedBuffer.ExampleUsage();

    @Test
    void testExampleUsage() {
        ConcurrentWorkStream.commonWorkStreams(
            () -> exampleUsage.putBuffer(),
            () -> exampleUsage.useBuffer()
        ).doWork();
    }

}
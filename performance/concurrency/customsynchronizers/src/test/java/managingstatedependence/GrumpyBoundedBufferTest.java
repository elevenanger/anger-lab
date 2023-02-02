package managingstatedependence;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
package executetaskinthread;

import cn.anger.util.concurrency.ConcurrentWorkStream;
import cn.anger.util.net.SocketUtil;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class WebServerTest {

    private static final String PROXY = "localhost";

    @Test
    void testSingleThreadWebServer() {
        ConcurrentWorkStream.commonWorkStream(() ->
            SocketUtil.socketClient(PROXY, 8081, "test")).doWork();
    }

    @Test
    void testThreadPerTaskWebServer() {
        ConcurrentWorkStream.commonWorkStream(() ->
            SocketUtil.socketClient(PROXY, 8082, "test")).doWork();
    }

    @Test
    void testExecutionWebServer() {
        ConcurrentWorkStream.commonWorkStream(() ->
            SocketUtil.socketClient(PROXY, 8083, "test\n")).doWork();
    }

    @Test
    void testLifeCycleWebServer() {
        ConcurrentWorkStream.commonWorkStream(() ->
            SocketUtil.socketClient(PROXY, 8084, "test")).doWork();
    }

    @Test
    void testShutDownLifeCycleWebServer() {
        ConcurrentWorkStream.singleWorkStream(() ->
            SocketUtil.socketClient(PROXY, 8084, "quit")).doWork();
    }

}
package executetaskinthread;

import cn.anger.concurrency.ConcurrentWorkStream;
import cn.anger.net.SocketUtil;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class WebServerTest {

    private static final String PROXY = "localhost";

    @Test
    void testSingleThreadWebServer() {
        ConcurrentWorkStream.commonWorkStream(() ->
            SocketUtil.socketClient(PROXY, 8081)).doWork();
    }

    @Test
    void testThreadPerTaskWebServer() {
        ConcurrentWorkStream.commonWorkStream(() ->
            SocketUtil.socketClient(PROXY, 8082)).doWork();
    }

    @Test
    void testExecutionWebServer() {
        ConcurrentWorkStream.commonWorkStream(() ->
            SocketUtil.socketClient(PROXY, 8083)).doWork();
    }

}
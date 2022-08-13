package client;

import org.junit.jupiter.api.Test;

/**
 * author : liuanglin
 * date : 2022/8/13 22:11
 * description :
 */
class MyRemoteClientTest {

    @Test
    void remoteCallTest() {
        MyRemoteClient client = new MyRemoteClient();
        client.go();
    }
}
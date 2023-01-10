package jvmshutdown.shutdownhook;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class PrintInfoTest {

    @Test
    void shutdownHookTest() {
        PrintInfo printInfo = new PrintInfo();
        printInfo.start();
    }
}
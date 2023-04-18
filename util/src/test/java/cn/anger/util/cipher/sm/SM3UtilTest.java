package cn.anger.util.cipher.sm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class SM3UtilTest {

    @Test
    void hash() {
        String data = "sm3 test";
        String hash = SM3Util.hash(data);
        Assertions.assertEquals(hash, SM3Util.hash(data));
    }
}
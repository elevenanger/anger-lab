package cn.anger.cipher.sm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SM3UtilTest {

    @Test
    void hash() {
        String data = "sm3 test";
        String hash = SM3Util.hash(data);
        assertEquals(hash, SM3Util.hash(data));
    }
}
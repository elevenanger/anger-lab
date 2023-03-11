package sm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SM3UtilTest {

    @Test
    void hash() {
        String data = "test sm3 digest";
        assertEquals(SM3Util.hash(data), SM3Util.hash(data));
        System.out.println(SM3Util.hash(data));
        System.out.println(SM3Util.hash(data).length());
    }

}
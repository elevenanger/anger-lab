package sm;

import org.junit.jupiter.api.Test;

import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SM4UtilTest {

    public static final String SM4_KEY = "5fe4175eba6d8d97762b36d372fa0a35";
    public static final String SRC_STR = "sm4 encrypt test";
    public static final String ENC_STR = "5952cdeede1dead814d8e88abdd3f71b4287bd9d81119e2e703d0db1596a71d3";

    @Test
    void generateKey() throws GeneralSecurityException {
        String key = SM4Util.generateKey();
        assertEquals(32, key.length());
        System.out.println(key);
    }

    @Test
    void encrypt() {
        String en = SM4Util.encrypt(SM4_KEY, SRC_STR);
        assertNotNull(en);
        System.out.println(en);
    }

    @Test
    void decrypt() {
        String src = SM4Util.decrypt(SM4_KEY, ENC_STR);
        assertEquals(SRC_STR, src);
        System.out.println(src);
    }

    @Test
    void verify() throws GeneralSecurityException {
        boolean isSame = SM4Util.verify(SM4_KEY, ENC_STR, SRC_STR);
        assertTrue(isSame);
    }
}
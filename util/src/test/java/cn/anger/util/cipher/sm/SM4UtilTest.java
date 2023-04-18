package cn.anger.util.cipher.sm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SM4UtilTest {

    private static String key;
    public static final String plainText = SM2UtilTest.class.getName();

    @BeforeAll
    static void setUp() {
        key = "fe6f915f273336a487548db7b364744c";
    }

    @Test
    void generateKey() {
        Assertions.assertDoesNotThrow(() -> SM4Util.generateKey());
    }

    @Test
    void encrypt() {
        Assertions.assertDoesNotThrow(() -> SM4Util.encrypt(key, plainText));
    }

    @Test
    void decrypt() {
        AtomicReference<String> decipheredText = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            String cipherText = SM4Util.encrypt(key, plainText);
            decipheredText.set(SM4Util.decrypt(key, cipherText));
        });

        assertEquals(plainText, decipheredText.get());
    }

    @Test
    void verify() {
        AtomicReference<Boolean> verifyResult = new AtomicReference<>(false);
        assertDoesNotThrow(() -> {
            String cipherText = SM4Util.encrypt(key, plainText);
            verifyResult.set(SM4Util.verify(key, cipherText, plainText));
        });
        assertTrue(verifyResult.get());
    }

    @Test
    void encryptConf() {
        assertDoesNotThrow(() -> {
            System.out.println(SM4Util.encrypt(key, "ABCDEFGHIJKLMNOPQRST"));
            System.out.println(SM4Util.encrypt(key, "abcdefghijklmnopqrstuvwxyz0123456789ABCD"));
            System.out.println(SM4Util.encrypt(key, "2l9FJ0QDKRWb2CAp"));
            System.out.println(SM4Util.encrypt(key, "dkSGpQnTe5Yc9jJI38YBrTjpodIyNe5j"));
        });
    }

}
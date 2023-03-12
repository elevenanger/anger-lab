package cn.anger.cipher.sm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SM2UtilTest {
    private static String publicKey;
    private static String privateKey;
    public static final String plainText = SM2UtilTest.class.getName();

    @BeforeAll
    static void setUp() throws GeneralSecurityException {
        KeyPair pair = SM2Util.generateKeyPair();
        publicKey = SM2Util.getPublicKeyHexString(pair.getPublic());
        privateKey = SM2Util.getPrivateKeyHexString(pair.getPrivate());
        System.out.println("pubKey => " + publicKey);
        System.out.println("priKey => " + privateKey);
    }

    @Test
    void generateKeyPair() {
        assertDoesNotThrow((SM2Util::generateKeyPair));
    }

    @Test
    void getPrivateKeyHexString() {
        AtomicReference<String> privateKeyString = new AtomicReference<>();
        assertDoesNotThrow((() -> {
            KeyPair keyPair = SM2Util.generateKeyPair();
            privateKeyString.set(SM2Util.getPrivateKeyHexString(keyPair.getPrivate()));
        }));
        assertNotNull(privateKeyString.get());
        assertEquals(64, privateKeyString.get().length());
        System.out.println(privateKeyString.get());
    }

    @Test
    void getPublicKeyHexString() {
        AtomicReference<String> publicKeyString = new AtomicReference<>();
        assertDoesNotThrow((() -> {
            KeyPair keyPair = SM2Util.generateKeyPair();
            publicKeyString.set(SM2Util.getPublicKeyHexString(keyPair.getPublic()));
        }));
        assertNotNull(publicKeyString.get());
        assertTrue(publicKeyString.get().startsWith("04"));
        assertEquals(130, publicKeyString.get().length());
        System.out.println(publicKeyString.get());
    }

    @Test
    void encrypt() {
        AtomicReference<String> cipherData = new AtomicReference<>();
        assertDoesNotThrow(() -> cipherData.set(SM2Util.encrypt(publicKey, plainText)));
        System.out.println(cipherData.get());
    }

    @Test
    void decrypt() {
        AtomicReference<String> decipherData = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            String cipherData = SM2Util.encrypt(publicKey, plainText);
            decipherData.set(SM2Util.decrypt(privateKey, cipherData));
        });

        assertEquals(plainText, decipherData.get());
    }

    @Test
    void sign() {
        AtomicReference<String> signedData = new AtomicReference<>();
        assertDoesNotThrow(() -> signedData.set(SM2Util.sign(plainText, privateKey)), signedData.get());
    }

    @Test
    void verify() {
        AtomicReference<Boolean> verifyResult = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            String signedData = SM2Util.sign(plainText, privateKey);
            verifyResult.set(SM2Util.verify(plainText, signedData, publicKey));
        });
        assertTrue(verifyResult.get());
    }
}
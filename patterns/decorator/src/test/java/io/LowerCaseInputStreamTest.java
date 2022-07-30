package io;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Anger
 */
class LowerCaseInputStreamTest {

    @Test
    void test() {
        int c;
        try (InputStream in =
                new LowerCaseInputStream(
                    new BufferedInputStream(
                            Files.newInputStream(
                                Paths.get("./pom.xml")))))
        {
            while ((c = in.read()) > 0) {
                System.out.print((char) c);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
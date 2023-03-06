package cn.anger.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author : anger
 */
class FileSizeTest {

    @Test
    void getFileSizeAdaptively() throws IOException {
        String file = "/Users/liuanglin/data/pom.xml";
        String size = FileSize.toFixedString(file.length());


        Files.walk(Paths.get("/Users/liuanglin/data"))
            .map(Path::toFile)
            .map(File::length)
            .map(FileSize::toFixedString)
            .forEach(System.out::println);
    }

}
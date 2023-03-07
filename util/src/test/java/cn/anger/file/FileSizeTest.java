package cn.anger.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author : anger
 */
class FileSizeTest {

    @Test
    void getFileSizeAdaptively() throws IOException {
        String file = "/Users/liuanglin/data/pom.xml";
        String size = FileSize.toFixed(file.length());


        Files.walk(Paths.get("/Users/liuanglin/data"))
            .map(Path::toFile)
            .map(File::length)
            .map(FileSize::toFixed)
            .forEach(System.out::println);
    }


    @Test
    void testFileSize() {
        long oriSize = 100_000_000;
        Arrays.stream(FileSize.values())
            .forEach(v -> System.out.println(v.toSize(oriSize)));
    }


    @Test
    void testSizeTransform() {
        long oriSize = 1L << 40;

        double byteSize = FileSize.BYTE.toSize(oriSize);

        double kiloByteSize = FileSize.BYTE.toSize(byteSize, FileSize.KILO_BYTE);

        double megaByteSize = FileSize.KILO_BYTE.toSize(kiloByteSize, FileSize.MEGA_BYTE);

        double gigaByteSize = FileSize.MEGA_BYTE.toSize(megaByteSize, FileSize.GIGA_BYTE);

        double teraByteSize = FileSize.GIGA_BYTE.toSize(gigaByteSize, FileSize.TERA_BYTE);

        System.out.println(byteSize);

        System.out.println(kiloByteSize);

        System.out.println(megaByteSize);

        System.out.println(gigaByteSize);

        System.out.println(teraByteSize);

        System.out.println(FileSize.toFixed(oriSize));
    }


}
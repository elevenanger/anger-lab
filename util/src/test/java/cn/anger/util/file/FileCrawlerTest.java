package cn.anger.util.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : anger
 */
class FileCrawlerTest {

    @Test
    void crawlTest() throws IOException {
        final String root = "./target/";

        FileCrawler crawler = new FileCrawler(new File(root));

        List<File> files = new ForkJoinPool().invoke(crawler);

        long count;

        try (Stream<Path> pathStream = Files.walk(Paths.get(root))){
            count = pathStream.parallel()
                .map(Path::toFile)
                .filter(File::isFile)
                .map(File::getPath)
                .map(s -> s.replace(root, ""))
                .peek(System.out::println)
                .count();
        }

        assertEquals(files.size(), count);
    }

    @Test
    void testPathConverter() {
        Path path = Paths.get("");
        System.out.println(path.getFileSystem());
    }

}
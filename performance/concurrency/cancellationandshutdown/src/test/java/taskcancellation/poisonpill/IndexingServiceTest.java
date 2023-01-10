package taskcancellation.poisonpill;

import org.junit.jupiter.api.Test;
import stopthreadbasedservice.poisonpill.IndexingService;

import java.io.File;

/**
 * @author : anger
 */
class IndexingServiceTest {

    @Test
    void indexFileTest() throws InterruptedException {
        IndexingService indexingService = new IndexingService(
            file -> file.getAbsolutePath().contains("new"),
            new File("./"));
        indexingService.start();
        indexingService.awaitTermination();
    }
}
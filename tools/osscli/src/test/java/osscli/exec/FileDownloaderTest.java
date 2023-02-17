package osscli.exec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : anger
 */
@SpringBootTest
class FileDownloaderTest {

    @Autowired
    FileDownloader fileDownloader;

    @Test
    void testDownload() {
        fileDownloader.batchDownload("angersbucket", "", "/Users/liuanglin/data/tmp");
    }

}
package seqaws.exec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : anger
 */
@SpringBootTest
class FileDownloadTest {

    @Autowired
    FileDownload fileDownload;

    @Test
    void testDownload() {
        fileDownload.batchDownload("angersbucket", "", "/Users/liuanglin/data/tmp");
    }

}
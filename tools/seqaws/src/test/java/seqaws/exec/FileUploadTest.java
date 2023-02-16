package seqaws.exec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
@SpringBootTest
class FileUploadTest {

    @Autowired
    FileUpload fileUpload;

    @Test
    void batchUpload() {
        fileUpload.batchUpload("angersbucket", "/Users/liuanglin/data/sofa-registry");
    }

}
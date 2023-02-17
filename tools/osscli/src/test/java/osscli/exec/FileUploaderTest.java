package osscli.exec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : anger
 */
@SpringBootTest
class FileUploaderTest {

    @Autowired
    FileUploader fileUpload;

    @Test
    void batchUpload() {
        fileUpload.batchUpload("angersbucket", "/Users/liuanglin/data/sofa-registry");
    }

}
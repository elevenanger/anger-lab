package seqaws.object;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Anger
 * created on 2023/2/15
 */
@SpringBootTest
class ObjectBaseTest {

    @Autowired
    ObjectBase objectBase;

    @Test
    void getObject() {
        objectBase.getObject("angersbucket", "D.jpeg");
    }
}
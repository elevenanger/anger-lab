package osscli.object;

import cn.anger.concurrency.ConcurrentWorkStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Anger
 * created on 2023/2/15
 */
@SpringBootTest
class ObjectBaseTest {

    @Autowired
    ObjectBase objectBase;

    @Test
    void getObjectWithBuffer() {
        ConcurrentWorkStream
            .singleWorkStream(() ->
                objectBase.getObjectWithBuffer("angersbucket", "caozuo.docx", "/Users/liuanglin/data/tmp"))
            .doWork();
    }

    @Test
    void getObjectWithNioBuffer() {
        ConcurrentWorkStream.singleWorkStream(() ->
                objectBase.getObjectWithNioBuffer("angersbucket", "caozuo.docx", "/Users/liuanglin/data/tmp"))
            .doWork();
    }

}
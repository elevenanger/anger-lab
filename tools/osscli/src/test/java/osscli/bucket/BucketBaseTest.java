package osscli.bucket;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Anger
 * created on 2023/2/15
 */
@SpringBootTest
class BucketBaseTest {

    @Autowired
    BucketBase bucketBase;

    @Test
    void listObjects() {
        List<S3ObjectSummary> summaries = bucketBase.listObjects("angersbucket", "");
        System.out.println(summaries.size());
        summaries.forEach(System.out::println);
    }
}
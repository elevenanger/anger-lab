package osscli.services.aws;

import org.junit.jupiter.api.Test;
import osscli.services.Oss;
import osscli.services.model.OssConfiguration;
import osscli.services.model.ListAllObjectsResponse;
import osscli.services.model.PutObjectResponse;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author : anger
 */
class SeqAwsTest {

    private final Oss aws = new SeqAws(new OssConfiguration().withEndPoint("https://fldev.dgcb.com.cn:8080"));

    private static final String ANGERS_BUCKET = "angersbucket";

    @Test
    void listAllObjects() {
        ListAllObjectsResponse response = aws.listAllObjects(ANGERS_BUCKET, "");
        assertTrue(response.getObjectSummaryList().size() > 0);

        response.getObjectSummaryList().forEach(System.out::println);
    }

    @Test
    void putObject() {
        PutObjectResponse response =
            aws.putObject(ANGERS_BUCKET, new File("/Users/liuanglin/Desktop/2023.01.25-2023.02.23.csv"));
        assertNotNull(response.getETag());
    }


}
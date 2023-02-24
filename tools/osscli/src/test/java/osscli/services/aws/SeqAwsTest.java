package osscli.services.aws;

import org.junit.jupiter.api.Test;
import osscli.services.Oss;
import osscli.services.model.ListAllObjectsResponse;
import osscli.services.model.PutObjectResponse;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.PrimitiveIterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SeqAwsTest {

    private final Oss aws = new SeqAws();

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
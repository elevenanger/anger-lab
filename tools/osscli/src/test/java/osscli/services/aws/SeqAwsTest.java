package osscli.services.aws;

import org.junit.jupiter.api.Test;
import osscli.services.Oss;
import osscli.services.model.ListAllObjectsResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SeqAwsTest {

    private final Oss aws = new SeqAws();

    @Test
    void listAllObjects() {
        ListAllObjectsResponse response = aws.listAllObjects("angersbucket", "");
        assertTrue(response.getObjectSummaryList().size() > 0);

        response.getObjectSummaryList().forEach(System.out::println);
    }
}
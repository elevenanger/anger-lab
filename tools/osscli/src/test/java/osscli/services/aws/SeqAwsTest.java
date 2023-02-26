package osscli.services.aws;

import org.junit.jupiter.api.Test;
import osscli.services.Oss;
import osscli.services.model.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author : anger
 */
class SeqAwsTest {

    private final Oss aws = new SeqAws(new OssConfiguration().withEndPoint("http://192.168.48.129:8002/"));

    private static final String BUCKET = "local";

    @Test
    void listAllObjects() {
        ListAllObjectsResponse response = aws.listAllObjects(BUCKET, "");
        assertTrue(response.getObjectSummaryList().size() > 0);

        response.getObjectSummaryList().forEach(System.out::println);
    }

    @Test
    void putObject() {
        PutObjectResponse response =
            aws.putObject(BUCKET, new File("/Users/liuanglin/Desktop/2023.01.25-2023.02.23.csv"));
        assertNotNull(response.getETag());
    }


    @Test
    void downloadObject() {
        DownloadObjectResponse response =
                aws.downloadObject(BUCKET, "BREAKING_CHANGES.md", "/Users/liuanglin/data");
        assertNotNull(response.getBucket());
        System.out.println(response);
    }

    @Test
    void batchUpload() {
        BatchOperationResponse response = aws.batchUpload(BUCKET, "/Users/liuanglin/helm-charts");
        assertNotNull(response.getUploadResults());

        System.out.println(response);
    }

    @Test
    void listBuckets() {
        ListBucketsResponse response = aws.listBuckets();
        assertNotNull(response);

        System.out.println(response);
    }
}
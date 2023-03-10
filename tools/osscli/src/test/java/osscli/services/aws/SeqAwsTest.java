package osscli.services.aws;

import org.junit.jupiter.api.Test;
import osscli.services.Oss;
import osscli.services.config.OssConfigurationStore;
import osscli.services.model.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SeqAwsTest {

    private final Oss aws = new SeqAws(OssConfigurationStore.getOne("minio"));

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
            aws.putObject(BUCKET, new File("/Users/liuanglin/Desktop/soft.zip"));
        assertNotNull(response.getETag());
    }

    @Test
    void putObjectWithInputStream() throws IOException {
        PutObjectRequest request = new PutObjectRequest();
        request.setBucketName(BUCKET);
        File file = new File("/Users/liuanglin/Desktop/soft.zip");
        request.setFile(file);
        System.out.println(file.length());
        request.setKey(file.getName());
        request.setInputStream(new BufferedInputStream(Files.newInputStream(file.toPath()), Oss.BUFFER_SIZE));

        PutObjectResponse response = aws.putObject(request);
        assertNotNull(response.getETag());
    }


    @Test
    void downloadObject() {
        DownloadObjectResponse response =
                aws.downloadObject(BUCKET, "local.csv", "/Users/liuanglin/data");
        assertNotNull(response.getBucket());
        System.out.println(response);
    }

    @Test
    void batchUpload() {
        BatchOperationResponse response = aws.batchUpload(BUCKET, "/Users/liuanglin/helm-charts");
        assertNotNull(response.getUploadResults());
    }

    @Test
    void listBucketTest() {
        ListBucketsResponse response = aws.listBuckets();
        assertNotNull(response);

        System.out.println(response);
    }

    @Test
    void createBucketTest() {
        String bucket = "test";

        PutBucketRequest request = new PutBucketRequest(bucket);
        PutBucketResponse response = aws.createBucket(request);
        assertNotNull(response);

        System.out.println(response);

        aws.deleteBucket(bucket);
    }

    @Test
    void putObjectTest() {
        PutObjectRequest request = new PutObjectRequest();
        request.setBucketName("local");
        request.setKey("test.file");
        request.setFile(new File("/Users/liuanglin/data/test.file"));

        PutObjectResponse response = aws.putObject(request);
        assertNotNull(response);

        System.out.println(response.getKey());
    }

    @Test
    void listObjects() {
        ListObjectsRequest request = new ListObjectsRequest("local", "");
        ListObjectsResponse response = aws.listObjects(request);
        assertNotNull(response);
        assertEquals(response.getObjectSummaries().size(), 1000);
    }

}
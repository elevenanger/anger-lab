package osscli.services.cos;

import org.junit.jupiter.api.Test;
import osscli.services.config.OssConfigurationStore;
import osscli.services.model.*;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author Anger
 * created on 2023/4/17
 */
class COSServiceTest {

    private final COSService cosService = new COSService(OssConfigurationStore.getOne("COS_ANGER"));

    private static final String BUCKET = "anger-1317673019";

    public static final String DOWN_PATH = "/Users/liuanglin/data/down/";

    @Test
    void createBucket() {
        AtomicReference<PutBucketResponse> response = new AtomicReference<>();
        assertDoesNotThrow(() -> response.set(cosService.createBucket("test")));
        System.out.println(response.get());
    }

    @Test
    void listBuckets() {
        AtomicReference<ListBucketsResponse> response = new AtomicReference<>();
        assertDoesNotThrow(() -> response.set(cosService.listBuckets()));
        System.out.println(response.get());
    }

    @Test
    void putObject() {
        AtomicReference<PutObjectResponse> response = new AtomicReference<>();
        assertDoesNotThrow(() -> response.set(
                cosService.putObject("anger-1317673019",
                                                new File("/Users/liuanglin/data/slam_liu.jpeg"))));
        System.out.println(response.get());
    }

    @Test
    void downloadObject() {
        AtomicReference<DownloadObjectResponse> response = new AtomicReference<>();
        assertDoesNotThrow(() -> response.set(
                cosService.downloadObject(BUCKET,
                                    "slam_liu.jpeg",
                                    DOWN_PATH,
                                    "imageMogr2/thumbnail/!50p")));
        System.out.println(response.get());
    }

    @Test
    void deleteObject() {
        AtomicReference<DeleteObjectResponse> response = new AtomicReference<>();
        assertDoesNotThrow(() -> response.set(cosService.deleteObject(BUCKET, "slam_liu.jpeg")));
        System.out.println(response.get());
    }

    @Test
    void listObjects() {
        AtomicReference<ListObjectsResponse> response = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            response.set(cosService.listObjects(new ListObjectsRequest(BUCKET, "")));
        });
        System.out.println(response.get().getObjectSummaries().size());
    }

    @Test
    void listAllObjects() {
        AtomicReference<ListAllObjectsResponse> response = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            response.set(cosService.listAllObjects(BUCKET));
        });
        System.out.println(response.get());
    }

    @Test
    void batchDownload() {
        AtomicReference<BatchOperationResponse> response = new AtomicReference<>();
        assertDoesNotThrow(() -> response.set(cosService.batchDownload(BUCKET, DOWN_PATH, "")));
        System.out.println(response.get());
    }

    @Test
    void batchDelete() {
        AtomicReference<BatchOperationResponse> response = new AtomicReference<>();
        assertDoesNotThrow(() -> response.set(cosService.batchDelete(BUCKET, "target")));
        System.out.println(response.get());
    }
}
package osscli.services.cos;

import org.junit.jupiter.api.Test;
import osscli.services.config.OssConfigurationStore;
import osscli.services.model.DownloadObjectResponse;
import osscli.services.model.ListBucketsResponse;
import osscli.services.model.PutObjectResponse;

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
                                    "/Users/liuanglin/data/down/",
                                    "imageMogr2/thumbnail/!50p")));
        System.out.println(response.get());
    }
}
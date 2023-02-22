package osscli.exec;

import org.junit.jupiter.api.Test;
import osscli.exception.OssBaseException;
import osscli.services.batch.BatchFlow;
import osscli.services.model.DownloadObjectResponse;
import osscli.services.model.PutObjectResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class BatchFlowTest {

    private final BatchFlow flow = new BatchFlow();

    @Test
    void testBatchUpload() {
        List<CompletableFuture<PutObjectResponse>> futures = flow.batchUpload("angersbucket", "/Users/liuanglin/data/test");

        futures.forEach(
            future -> {
                try {
                    PutObjectResponse response = future.get();
                    assertNotNull(response.getETag());
                    System.out.println(response.getETag() + " " +  response.getResponseTime());
                } catch (InterruptedException | ExecutionException e) {
                    throw new OssBaseException(e);
                }
            }
        );
    }

    @Test
    void testBatchDownload() {
        List<CompletableFuture<DownloadObjectResponse>> futures =
            flow.batchDownload("angersbucket", "", "/Users/liuanglin/data/tmp");

        futures.forEach(future -> {
            try {
                DownloadObjectResponse response = future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
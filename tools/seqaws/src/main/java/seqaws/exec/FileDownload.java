package seqaws.exec;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seqaws.bucket.BucketBase;
import seqaws.object.ObjectBase;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author : anger
 */
@Component
public class FileDownload {
    private final ExecutorService exec = Executors.newCachedThreadPool();
    @Autowired
    private ObjectBase objectBase;
    @Autowired
    private BucketBase bucketBase;

    public void batchDownload(String bucket, String prefix, String downloadPath) {
        List<S3ObjectSummary> objectSummaryList = bucketBase.listObjects(bucket, prefix);
        List<Future<?>> futures = objectSummaryList.stream()
            .map(S3ObjectSummary::getKey)
            .map(key -> exec.submit(() -> objectBase.getObjectWithBuffer(bucket, key, downloadPath)))
            .collect(Collectors.toList());

            futures.forEach(future -> {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
    }

}

package osscli.exec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osscli.object.ObjectBase;
import osscli.services.aws.SeqAwsBucketService;
import osscli.services.model.bucket.ListObjectRequest;
import osscli.services.model.object.ObjectSummary;

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
public class FileDownloader {
    private final ExecutorService exec = Executors.newCachedThreadPool();
    @Autowired
    private ObjectBase objectBase;

    public void batchDownload(String bucket, String prefix, String downloadPath) {
        ListObjectRequest request = new ListObjectRequest();
        request.setBucketName(bucket);
        request.setPrefix(prefix);

        List<ObjectSummary> objectSummaryList = new SeqAwsBucketService().listObjects(request).getObjectSummaries();

        List<Future<?>> futures = objectSummaryList.stream()
            .map(ObjectSummary::getKey)
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

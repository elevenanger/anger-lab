package osscli.exec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osscli.object.ObjectBase;
import osscli.services.Oss;
import osscli.services.aws.SeqAws;
import osscli.services.model.ListObjectRequest;
import osscli.services.model.ObjectSummary;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author : anger
 */
@Component
public class FileDownloader {
    private final ExecutorService exec = Executors.newCachedThreadPool();
    @Autowired
    private ObjectBase objectBase;

    private final Oss aws = new SeqAws();

    public void batchDownload(String bucket, String prefix, String downloadPath) {
        ListObjectRequest request = new ListObjectRequest();
        request.setBucketName(bucket);
        request.setPrefix(prefix);

        List<ObjectSummary> objectSummaryList = aws.listObjects(request).getObjectSummaries();

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

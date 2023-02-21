package osscli.exec;

import org.springframework.stereotype.Component;
import osscli.services.Oss;
import osscli.services.aws.SeqAws;
import osscli.services.model.ListObjectRequest;
import osscli.services.model.ObjectSummary;
import osscli.services.model.PutObjectRequest;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.runAsync;

/**
 * @author : anger
 */
@Component
public class BatchFlow {

    private final Oss aws = new SeqAws();

    public List<CompletableFuture<Void>> batchUpload(String bucket, String filePath) {
        return Arrays.stream(Objects.requireNonNull(new File(filePath).listFiles()))
                .map(file -> new PutObjectRequest(bucket, file.getName(), file))
                .map(request -> runAsync(() -> aws.putObject(request)))
                .collect(Collectors.toList());
    }

    public List<CompletableFuture<Void>> batchDownload(String bucket, String prefix, String downloadPath) {
        List<ObjectSummary> objectSummaryList =
                aws.listObjects(new ListObjectRequest(bucket, prefix))
                    .getObjectSummaries();

        return objectSummaryList.stream()
                .map(ObjectSummary::getKey)
                .map(key -> runAsync(() -> aws.downloadObject(bucket, key, downloadPath)))
                .collect(Collectors.toList());
    }

}

package osscli.services.batch;

import osscli.services.Oss;
import osscli.services.aws.SeqAws;
import osscli.services.model.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * @author : anger
 * 批量操作
 */
public class BatchFlow {

    private final Oss aws = new SeqAws();

    public List<CompletableFuture<PutObjectResponse>> batchUpload(String bucket, String filePath) {
        return Arrays.stream(Objects.requireNonNull(new File(filePath).listFiles()))
                .map(file -> new PutObjectRequest(bucket, file.getName(), file))
                .map(request -> supplyAsync(() -> aws.putObject(request)))
                .collect(Collectors.toList());
    }

    public List<CompletableFuture<DownloadObjectResponse>> batchDownload(String bucket, String prefix, String downloadPath) {
        List<ObjectSummary> objectSummaryList =
                aws.listObjects(new ListObjectsRequest(bucket, prefix))
                    .getObjectSummaries();

        return objectSummaryList.stream()
                .map(ObjectSummary::getKey)
                .map(key -> supplyAsync(() -> aws.downloadObject(bucket, key, downloadPath)))
                .collect(Collectors.toList());
    }

}

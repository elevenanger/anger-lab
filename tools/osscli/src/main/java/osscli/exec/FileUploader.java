package osscli.exec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import osscli.object.ObjectBase;
import osscli.services.Oss;
import osscli.services.aws.SeqAws;
import osscli.services.model.PutObjectRequest;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author : anger
 */
@Component
public class FileUploader {

    private final ExecutorService exec = Executors.newCachedThreadPool();
    @Autowired
    ObjectBase objectBase;

    private final Oss aws = new SeqAws();

    public void batchUpload(String bucket, String filePath) {

        List<Future<?>> files = Arrays.stream(Objects.requireNonNull(new File(filePath).listFiles()))
            .map(file -> {
                PutObjectRequest request = new PutObjectRequest();
                request.setFile(file);
                request.setKey(file.getName());
                request.setBucketName(bucket);
                return request;
            })
            .map(request -> exec.submit(() -> aws.putObject(request)))
            .collect(Collectors.toList());

        files.forEach(future -> {
            try {
                future.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

}

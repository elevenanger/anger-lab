package seqaws.exec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seqaws.object.ObjectBase;

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
public class FileUpload {

    private final ExecutorService exec = Executors.newCachedThreadPool();
    @Autowired
    ObjectBase objectBase;

    public void batchUpload(String bucket, String filePath) {
        List<Future<?>> files = Arrays.stream(Objects.requireNonNull(new File(filePath).listFiles()))
            .map(file ->
                exec.submit(() -> objectBase.putObjectWithFile(bucket, file.getName(), file)))
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

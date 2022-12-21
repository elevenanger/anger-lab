package cn.anger.synchronizers.latches.futuretask;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author : anger
 * FutureTask 一般在 Executor 中用来表示异步任务，
 * 但是其实可以用于任何的耗时较长的任务可以在获取结果之前启动任务。
 */
public class Preloader {
    private final FutureTask<ProductInfo> future =
        new FutureTask<>(new Callable<ProductInfo>() {
            @Override
            public ProductInfo call() throws DataloadException{
                return loadProductInfo();
            }
        });
    private final Thread thread = new Thread(future);

    public void start() { thread.start(); }

    public ProductInfo get()
        throws DataloadException {
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataloadException)
                throw (DataloadException) cause;
            else
                throw launderThrowable(cause);
        }
    }

    private ProductInfo loadProductInfo() throws DataloadException {
        ProductInfo productInfo = new ProductInfo();
        try {
            Files.readAllLines(
                Paths.get("performance/concurrency/buildingblocks/src/main/resources/",
                    "productinfo.dat")).stream()
                .map(s -> s.split(" "))
                .forEach(strings -> productInfo.add(strings[0], new BigDecimal(strings[1])));
        } catch (IOException e) {
            throw new DataloadException(e.getMessage());
        }
        return productInfo;
    }

    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not checked", t);
    }

}

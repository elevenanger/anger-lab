package threadpoolexecutors;

import cn.anger.concurrency.ThreadUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;

/**
 * @author : anger
 * 通过不同类型的 Executor 来执行任务
 */
public class PrintInfoExec {
    private final ExecutorService exec;

    public PrintInfoExec(ExecutorService exec) {
        this.exec = exec;
    }

    public void printInfo(String info, int times) {
        GenerateInfoTask task = new GenerateInfoTask(info);
        Stream.generate(() -> exec.submit(task))
            .parallel()
            .limit(times)
            .forEach(future -> {
                try {
                    System.out.printf("printer %s",
                        future.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    private static class GenerateInfoTask implements Callable<String> {
        private final String info;
        public GenerateInfoTask(String info) {
            this.info = info;
        }

        @Override
        public String call() throws Exception {
            ThreadUtil.sleep(1);
            return String.format("%s info => %s%n",
                Thread.currentThread().getName(),
                info);
        }
    }
}

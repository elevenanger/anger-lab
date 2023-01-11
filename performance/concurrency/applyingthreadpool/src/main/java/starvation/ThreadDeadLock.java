package starvation;

import java.util.concurrent.*;

/**
 * @author : anger
 * 单线程 Executor 导致的任务死锁
 */
public class ThreadDeadLock {
    // 单线程 Executor
    private final ExecutorService exec = Executors.newSingleThreadExecutor();

    public void printPageInfo() throws ExecutionException, InterruptedException {
        // 提交任务，因为任务中又会提交子任务，并且等待子任务结束，在单线程 Executor 中，会导致死锁
        Future<String> pageInfo = exec.submit(new RenderPageTask());
        System.out.println(pageInfo.get());
    }

    private static class LoadFileTask implements Callable<String> {
        private final String fileName;

        public LoadFileTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String call() throws Exception {
            return String.format("Loaded file %s%n", fileName);
        }
    }

    private class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            // 主任务提交两个子任务，分别渲染 header 和 footer
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // 将会导致死锁 -- 任务等待子任务完成
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            return "body\n";
        }
    }
}

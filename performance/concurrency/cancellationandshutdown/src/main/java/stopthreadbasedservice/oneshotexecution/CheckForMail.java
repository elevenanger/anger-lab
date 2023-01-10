package stopthreadbasedservice.oneshotexecution;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author : anger
 * 使用 private Executor
 * 它的生命周期约束在方法调用中
 */
public class CheckForMail {
    public boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit)
        throws InterruptedException {
        // 私有的 Executor
        ExecutorService exec = Executors.newCachedThreadPool();
        final AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            // 检查所有 host 的邮件,提交任务
            for (String host : hosts) {
                exec.execute(() -> {
                    if (checkMail(host))
                        hasNewMail.set(true);
                });
            }
        } finally {
            // 所有的任务执行完成之后终止 Executor
            exec.shutdown();
            exec.awaitTermination(timeout, unit);
        }
        return hasNewMail.get();
    }

    private boolean checkMail(String host) {
        System.out.printf("%s mail has checked.%n", host);
        return true;
    }
}

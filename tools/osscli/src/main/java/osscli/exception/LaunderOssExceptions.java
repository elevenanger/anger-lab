package osscli.exception;

/**
 * @author Anger
 * created on 2023/2/15
 * 通用 oss 异常处理类
 */
public class LaunderOssExceptions {

    private LaunderOssExceptions() {}

    public static void launder(Throwable e) {
        if (e instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        } else
            throw new OssBaseException(e);
    }
}

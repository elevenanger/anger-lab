package osscli.exception;

import com.amazonaws.AmazonServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Anger
 * created on 2023/2/15
 * 通用 aws 异常处理类
 */
public class LaunderAwsExceptions {

    private LaunderAwsExceptions() {}

    public static void launder(Throwable e) {
        if (e instanceof InterruptedException) {
            Thread.currentThread().interrupt();
        } else
            throw new OssBaseException(e);
    }
}

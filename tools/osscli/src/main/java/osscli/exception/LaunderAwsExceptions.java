package osscli.exception;

import com.amazonaws.AmazonServiceException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Anger
 * created on 2023/2/15
 * 通用 aws 异常处理类
 */
@Slf4j
public class LaunderAwsExceptions {

    private LaunderAwsExceptions() {}

    public static void launder(Throwable e) {
        if (e instanceof AmazonServiceException) {
            AmazonServiceException ex = (AmazonServiceException) e;
            log.error("status code : {} error code : {} error message : {}",
                ex.getStatusCode(), ex.getErrorCode(), ex.getErrorMessage());
        } else
            throw new RuntimeException(e);
    }
}

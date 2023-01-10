package handleabnormalthreadtermination.logexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author : anger
 * UncaughtExceptionHandler
 * 线程抛出未捕获的异常打印日志
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Thread terminated with exception: " + t.getName(), e);
    }
}


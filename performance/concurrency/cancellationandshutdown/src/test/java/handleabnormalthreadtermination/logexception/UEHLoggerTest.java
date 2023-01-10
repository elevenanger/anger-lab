package handleabnormalthreadtermination.logexception;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class UEHLoggerTest {

    @Test
    void UEHTest() {
        Runnable exceptionTask = () -> {
            System.out.println("exception runnable");
            throw new RuntimeException();
        };

        Thread t = new Thread(exceptionTask);
        t.setUncaughtExceptionHandler(new UEHLogger());
        t.start();
    }
}
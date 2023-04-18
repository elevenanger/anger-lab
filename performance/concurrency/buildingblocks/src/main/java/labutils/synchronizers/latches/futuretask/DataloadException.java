package labutils.synchronizers.latches.futuretask;

import java.io.IOException;

/**
 * @author : anger
 */
public class DataloadException extends IOException {
    public DataloadException() {
        super();
    }
    public DataloadException(String message) {
        super(message);
    }
}

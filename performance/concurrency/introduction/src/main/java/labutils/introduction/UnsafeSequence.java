package labutils.introduction;

import labutils.annotation.NotThreadSafe;

/**
 * @author : anger
 */
@NotThreadSafe
public class UnsafeSequence {
    private int value;

    public int getNext() {
        return value++;
    }
}

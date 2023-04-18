package labutils.synchronizationcollectionproblem;

import labutils.annotation.ThreadSafe;

import java.util.Vector;

/**
 * @author : anger
 */
@ThreadSafe
public class SafeCompoundAction {
    public static Object getLast(Vector vector) {
        synchronized (vector) {
            int lastIndex = vector.size() - 1;
            return vector.get(lastIndex);
        }
    }
    public static void removeLast(Vector vector) {
        synchronized (vector) {
            int lastIndex = vector.size() - 1;
            vector.remove(lastIndex);
        }
    }
}

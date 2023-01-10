package jvmshutdown.finalizer;

/**
 * @author : anger
 */
public class ImportantObject {
    public void finalize() {
        System.out.println("cleaning important object.");
    }
}

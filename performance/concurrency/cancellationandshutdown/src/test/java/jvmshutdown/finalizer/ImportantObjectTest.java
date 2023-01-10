package jvmshutdown.finalizer;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class ImportantObjectTest {

    @Test
    void finalizeTest() {
        importantObjectUsing();
        Runtime.getRuntime().gc();
    }

    void importantObjectUsing() {
        ImportantObject object1 = new ImportantObject();
        ImportantObject object2 = new ImportantObject();
        ImportantObject object3 = new ImportantObject();
        ImportantObject object4 = new ImportantObject();
        ImportantObject object5 = new ImportantObject();
    }
}
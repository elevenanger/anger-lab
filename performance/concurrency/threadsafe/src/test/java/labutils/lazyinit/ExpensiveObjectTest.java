package labutils.lazyinit;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class ExpensiveObjectTest {

    final Supplier<ExpensiveObject> supplier = ExpensiveObject::new;

    @Test
    void testExpensiveOperation() {
        ExpensiveObject object = supplier.get();
        assertNotNull(object.getLargeData());
    }

}
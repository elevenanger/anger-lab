package functions;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

/**
 * author : liuanglin
 * date : 2022/7/10 09:46
 * description : Supplier 用法
 */
class SupplierTest {

    Supplier<String> stringSupplier = () -> "hello";

    @Test
    void testSupplier() {
        assertEquals("hello", stringSupplier.get());
    }
}



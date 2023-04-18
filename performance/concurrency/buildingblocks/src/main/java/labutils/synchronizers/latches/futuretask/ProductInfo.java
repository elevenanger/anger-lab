package labutils.synchronizers.latches.futuretask;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : anger
 */
public class ProductInfo {
    private final ConcurrentHashMap<String, BigDecimal> productInfo =
        new ConcurrentHashMap<>();

    public void add(String name, BigDecimal price) {
        productInfo.putIfAbsent(name, price);
    }

    public Map<String, BigDecimal> getProductInfo() {
        return Collections.unmodifiableMap(productInfo);
    }
}

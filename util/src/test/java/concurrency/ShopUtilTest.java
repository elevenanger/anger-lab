package concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static concurrency.ShopUtil.*;

/**
 * author : anger
 */
@Slf4j
class ShopUtilTest {
    private final Executor exec =
        Executors.newFixedThreadPool(
            Math.min(50, 100),
            r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;});
    private final List<Shop> shops =
        Arrays.asList(
            new Shop("Anger store"),
            new Shop("7-11"),
            new Shop("Quan jia"),
            new Shop("Quan jia"),
            new Shop("Quan jia"),
            new Shop("Walmart"),
            new Shop("Amazon"),
            new Shop("Quan jia"),
            new Shop("Walmart"),
            new Shop("Amazon"),
            new Shop("Quan jia"),
            new Shop("Walmart"),
            new Shop("Amazon"),
            new Shop("Quan jia"),
            new Shop("Walmart"),
            new Shop("Amazon"),
            new Shop("Walmart"),
            new Shop("Amazon"),
            new Shop("Quan jia"),
            new Shop("Walmart"),
            new Shop("Amazon"),
            new Shop("Quan jia"),
            new Shop("Walmart"),
            new Shop("Amazon"),
            new Shop("Quan jia"),
            new Shop("Walmart"),
            new Shop("Amazon"),
            new Shop("Walmart"),
            new Shop("Amazon"),
            new Shop("Ali"),
            new Shop("Hua Run"),
            new Shop("Family"));
    private final String product = "battery";
    long start;

    @BeforeEach
    void setStart() {
        start = System.nanoTime();
    }

    @AfterEach
    void afterEach() {
        log.info("处理时间：{} ms", (System.nanoTime() - start) / 1_000_000);
    }

    @Test
    void testFindPriceSync() {
        log.info("testFindPriceSync");
        log.info(findPricesSync(shops, product).toString());
    }

    @Test
    void testFindPricesParallel() {
        log.info("testFindPricesParallel");
        log.info(findPricesParallel(shops, product).toString());
    }

    @Test
    void testFindPricesFuture() {
        log.info("testFindPricesFuture");
        log.info(findPricesFuture(shops, product).toString());
    }

    @Test
    void testFindPricesWithExec() {
        log.info("testFindPricesWithExec");
        log.info(findPricesFutureWithSpecificExec(shops, product, exec).toString());
    }

    @Test
    void testFindPriceWithDiscount() {
        log.info("testFindPriceWithDiscount");
        log.info(findPricesWithDiscount(shops, product).toString());
    }

    @Test
    void testFindPriceWithDiscountFuture() {
        log.info("testFindPriceWithDiscountFuture");
        log.info(findPriceWithDiscountFuture(shops, product, exec).toString());
    }

    @Test
    void testQuicklyFind() {
        log.info("testQuicklyFind");
        findFastest(shops, product, exec);
    }
}
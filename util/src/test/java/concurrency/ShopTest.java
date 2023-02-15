package concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * author : anger
 * description : 异步测试
 */
@Slf4j
class ShopTest {

    Shop shop = new Shop("Anger store");
    String product = "macbook pro";

    @Test
    void sync() {
        log.info("开始计算价格");
        double price = shop.getPrice(product);
        doSomethingElse();
        log.info("价格：" +  price);
    }

    @Test
    void async() {
        log.info("开始计算价格");
        Future<Double> futurePrice = shop.getPriceAsync(product);
        Future<Double> futurePriceFac = shop.getPriceAsyncByFactory(product);
        // 在获取价格之前可以继续完成别的计算动作
        doSomethingElse();
        try {
            // 从 Future 中读取结果，价格未知则会发生阻塞
            double price = futurePrice.get();
            log.info("价格：" +  price);
            double price1 = futurePriceFac.get();
            log.info("工厂方法创建 Future 对象计算价格：" +  price);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void doSomethingElse() {
        log.info("干点别的。。。");
    }
}
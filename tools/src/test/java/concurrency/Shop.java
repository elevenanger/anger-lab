package concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * author : liuanglin
 * date : 2022/7/13 14:04
 * description : 同步和异步计算比较
 */
@Slf4j
public class Shop {

    private final String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    Random random = new Random();

    /*
    同步获取商品价格的方法
    将等待价格计算完成后返回结果
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPriceStr(String product) {
        double price = calculatePrice(product);
        Discount.Code code =
            Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", product, price, code);
    }
    /*
    异步计算商品的价格
     */
    public Future<Double> getPriceAsync(String product) {
        // 创建 CompletableFuture 对象，它会包含计算的结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        // 创建一个新的线程执行计算操作
        new Thread( () -> {
            try {
                double price = calculatePrice(product);
                /*
                需要长时间计算的结果，设置为 future 对象的返回值
                如果正常结束
                完成 Future 操作，设置商品价格
                 */
                futurePrice.complete(price);
            } catch (Exception e) {
                // 如果发生异常导致失败，则抛出异常，完成 Future 操作
                futurePrice.completeExceptionally(e);
            }
        }).start();
        // 无需等待还未结束的计算结果，直接返回 future 对象
        return futurePrice;
    }

    // 通过 CompletableFuture 工厂方法创建 CompletableFuture 对象
    public Future<Double> getPriceAsyncByFactory(String product) {
        /*
        supplyAsync() 接收一个 Supplier 作为参数
        返回一个 CompletableFuture 对象
        该对象异步执行后会调用 Supplier 方法返回值
        Supplier 方法会交由 ForkJoinPool 中的某个执行线程（Executor）执行
        也可以传递参数指定执行线程
         */
        return CompletableFuture.supplyAsync( () -> calculatePrice(product));
    }

    public double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    public void delay() {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSomethingElse() {
        log.info("干点别的。。。");
    }
}

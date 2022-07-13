package concurrency;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * author : liuanglin
 * date : 2022/7/13 14:59
 * description : 商店相关工具类
 */
public class ShopUtil {

    private ShopUtil(){}

    /*
    给定一个商店列表
    查找所有的商品价格
     */
    public static List<Double> findPricesSync(List<Shop> shops, String product) {
        return shops.stream()
                    .map(shop -> shop.getPrice(product))
                    .collect(Collectors.toList());
    }

    /*
    使用并行的方式获取同一个商品在所有商店的价格
    并行的方式使用的是通用的线程池
    拥有固定的线程池大小
     */
    public static List<Double> findPricesParallel(List<Shop> shops, String product) {
        return shops.parallelStream()
            .map(shop -> shop.getPrice(product))
            .collect(Collectors.toList());
    }

    /*
    使用 CompletableFuture 异步计算并获取所有的计算结果
    CompletableFuture 可以对 Executor 进行配置
    指定线程池的大小
     */
    public static List<Double> findPricesFuture(List<Shop> shops, String product) {
        // 使用一个流水线创建计算任务
        List<CompletableFuture<Double>> futures =
            shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getPrice(product)))
                .collect(Collectors.toList());
        // 使用另一条流水线获取计算结果，使得计算任务可以并行执行
        return futures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

    /*
    使用指定的线程池进行计算
     */
    public static List<Double> findPricesFutureWithSpecificExec(
        List<Shop> shops, String product, Executor executor) {
        List<CompletableFuture<Double>> futures =
            shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                    () -> shop.getPrice(product), executor))
                .collect(Collectors.toList());
        return futures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

    public static List<String> findPricesWithDiscount(List<Shop> shops, String product) {
        return shops.stream()
            .map(shop -> shop.getPriceStr(product))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(Collectors.toList());
    }

    public static List<String> findPriceWithDiscountFuture
        (List<Shop> shops, String product, Executor executor) {
        List<CompletableFuture<String>> futures =
            shops.stream()
                // 以异步的方式获取原始价格
                .map(shop -> CompletableFuture.supplyAsync(
                    () -> shop.getPriceStr(product)))
                // thenApply() 对结果进行进一步的操作，转换成 Quote 对象
                .map(future -> future.thenApply(Quote::parse))
                // 组合另一个 Future 异步任务申请折扣
                .map(future -> future.thenCompose(quote ->
                    CompletableFuture.supplyAsync(
                        () -> Discount.applyDiscount(quote), executor)
                        /*
                        thenCombine() 组合两个完全不相关的 Future
                        再通过一个 BIFunction 操作两个不相关的 Future
                         */
                        .thenCombine(CompletableFuture.supplyAsync(() -> " desc "),
                            (r1, r2) -> r1 + r2)))
                .collect(Collectors.toList());
        // 等待 Stream 中所有的 Future 任务执行完毕，提取执行结果
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}

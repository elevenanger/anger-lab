package concurrency;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author : anger
 * description : 商店相关工具类
 */
public class ShopUtil {

    private ShopUtil(){}

    /*
    串行计算
    给定一个商店列表
    返回相同商品在所有商店的商品价格列表
     */
    public static List<Double> findPricesSync(List<Shop> shops, String product) {
        return shops.stream()
                    .map(shop -> shop.getPrice(product))
                    .collect(Collectors.toList());
    }

    /*
    使用并行流的方式获取同一个商品在所有商店的价格
    并行的方式使用的是通用的线程池
    拥有固定的线程池大小
    parallelStream 默认线程池大小为可用处理器的数量
    Runtime.getRuntime().availableProcessors()
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
    使用指定的线程池进行并行计算
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

    /*
    串行计算
    增加折扣计算逻辑
     */
    public static List<String> findPricesWithDiscount(List<Shop> shops, String product) {
        return shops.stream()
            .map(shop -> shop.getPriceStr(product))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(Collectors.toList());
    }

    /*
    执行异步计算
    声明计算结果后续处理逻辑
    应用于不需要返回结果给调用方的场景
     */
    public static void findFastest(List<Shop> shops, String product, Executor executor) {
        Stream<CompletableFuture<String>> findPricesStream =
            shops.stream()
                .map(shop -> mapShopToPrice.apply(shop, product, executor));
        CompletableFuture<?>[] futures =
            /*
            thenAccept() 接收 Consumer 函数，定义如何处理 Future 的结果
            一旦一个 Future 返回结果计算完毕就立即执行逻辑
            返回一个 CompletableFuture<Void> 对象
             */
            findPricesStream.map(f -> f.thenAccept(System.out::println))
                .toArray(CompletableFuture[]::new);
        /*
        allOf() 方法接收一系列的 Future 对象，合并所有 Future 的结果
         */
        CompletableFuture.allOf(futures).join();
    }

    /*
    执行异步计算
    并且返回每个计算预期的结果给调用方
     */
    public static List<String> findPriceWithDiscountFuture
        (List<Shop> shops, String product, Executor executor) {
        List<CompletableFuture<String>> futures =
            shops.stream()
                .map(shop -> mapShopToPrice.apply(shop, product, executor))
                .collect(Collectors.toList());
        // 等待 Stream 中所有的 Future 任务执行完毕，提取执行结果
        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    /*
    三元函数
    接收 商店、字符串、线程池
    映射为 CompletableFuture<String> 对象
     */
    private final static TriFunc<Shop, String, Executor, CompletableFuture<String>>
        mapShopToPrice = (shop, product, executor) ->
            CompletableFuture.supplyAsync(
                // 以异步的方式获取原始价格
                () -> shop.getPriceStr(product), executor)
            // thenApply() 对结果进行进一步的操作，转换成 Quote 对象
            .thenApply(Quote::parse)
            // 组合另一个 Future 异步任务申请折扣
            .thenCompose(quote -> CompletableFuture.supplyAsync(
                () -> Discount.applyDiscount(quote), executor))
            /*
            thenCombine() 组合两个完全不相关的 Future
            再通过一个 BIFunction 操作两个不相关的 Future
            在这里给字符串拼接上货币单位
            */
            .thenCombine(CompletableFuture.supplyAsync(() -> " yuan "), (f1, f2) -> f1 + f2);

}

@FunctionalInterface
interface TriFunc<F, S, T, R> {
    R apply(F f, S s, T t);
}
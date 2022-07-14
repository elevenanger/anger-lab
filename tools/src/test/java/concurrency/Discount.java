package concurrency;

/**
 * author : liuanglin
 * description : 商品折扣类
 */
public class Discount {

    /*
    折扣代码-折扣级别
     */
    public enum Code {
        NONE(0), SILVER(5), GOLD(10),
        PLATINUM(15), DIAMOND(20);
        private final int percentage;
        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    /*
    应用折扣
     */
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + ":" + quote.getProduct() + " price is " +
            Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    /*
    折扣计算
    人为设置一个延时
     */
    private static double apply(double price, Code code) {
        delay();
        return price * (100 - code.percentage) / 100;
    }

    private static void delay() {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package concurrency;

/**
 * author : anger
 * description : 折扣信息
 */
public class Quote {
    private final String shopName;
    private final String product;
    private final double price;
    private final Discount.Code discountCode;

    private Quote(String shopName, String product, double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.product = product;
        this.price = price;
        this.discountCode = discountCode;
    }

    /*
    Quote 工厂方法，切割字符串创建对象
     */
    public static Quote parse(String priceStr) {
        String [] split = priceStr.split(":");
        String shopName = split[0];
        String product = split[1];
        double price = Double.parseDouble(split[2]);
        Discount.Code code = Discount.Code.valueOf(split[3]);
        return new Quote(shopName, product, price, code);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public String getProduct() {
        return product;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
}

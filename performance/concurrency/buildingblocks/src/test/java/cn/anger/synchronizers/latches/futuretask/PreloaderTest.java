package cn.anger.synchronizers.latches.futuretask;

import org.junit.jupiter.api.Test;

/**
 * @author : anger
 */
class PreloaderTest {

    @Test
    void productInfo() throws InterruptedException, DataloadException {
        Preloader preloader = new Preloader();
        preloader.start();

        System.out.println("do something...");
        System.out.println("do something...");
        System.out.println("do something...");


        ProductInfo productInfo = preloader.get();
        System.out.println(productInfo.getProductInfo());
    }
}
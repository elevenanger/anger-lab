package mvc.service;

import mvc.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author : liuanglin
 * date : 2022/7/22 14:55
 * description : 产品相关服务
 * 仅演示用
 * 后续需要改成数据库存储数据
 */
@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> findAll() {
        return products;
    }
}

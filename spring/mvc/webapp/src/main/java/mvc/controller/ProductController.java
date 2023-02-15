package mvc.controller;

import mvc.model.Product;
import mvc.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author : anger
 * date : 2022/7/22 14:57
 * description : 产品 controller
 */
@Controller
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public String viewProducts(Model model) {
        List<Product> products = service.findAll();
        model.addAttribute("products", products);
        return "products.html";
    }

    @PostMapping(path = "/products")
    public String addProduct(Model model, Product product) {
        service.addProduct(product);

        List<Product> products = service.findAll();
        model.addAttribute("products", products);
        return "products.html";
    }
}

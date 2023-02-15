package persist.controller;

import org.springframework.web.bind.annotation.*;
import persist.model.Purchase;
import persist.repository.PurchaseJdbcTemplateRepository;

import java.util.List;

/**
 * author : anger
 * date : 2022/7/25 11:33
 */
@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseJdbcTemplateRepository repository;

    public PurchaseController(PurchaseJdbcTemplateRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public void storePurchase(@RequestBody Purchase purchase) {
        repository.storePurchase(purchase);
    }

    @GetMapping
    public List<Purchase> findPurchases() {
        return repository.findAllPurchases();
    }
}

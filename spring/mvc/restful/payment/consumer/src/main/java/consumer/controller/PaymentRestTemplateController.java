package consumer.controller;

import consumer.proxy.RestTemplatePaymentProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import provider.model.Payment;

/**
 * author : anger
 * date : 2022/7/24 18:50
 * description : RestTemplate controller
 */
@Slf4j
@RestController
public class PaymentRestTemplateController {

    private final RestTemplatePaymentProxy proxy;

    public PaymentRestTemplateController(RestTemplatePaymentProxy proxy) {
        this.proxy = proxy;
    }

    @PostMapping("/payment/rest")
    public Payment createPayment(@RequestBody Payment payment) {
        return proxy.createPayment(payment);
    }
}

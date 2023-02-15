package consumer.controller;

import consumer.proxy.WebClientPaymentProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import provider.model.Payment;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * author : anger
 * date : 2022/7/24 20:49
 * description :
 */
@RestController
public class PaymentWebClientController {

    private final WebClientPaymentProxy webClientPaymentProxy;

    public PaymentWebClientController(WebClientPaymentProxy webClientPaymentProxy) {
        this.webClientPaymentProxy = webClientPaymentProxy;
    }

    @PostMapping("/payment/webclient")
    public Mono<Payment> createPayment(
        @RequestBody Payment payment) {
        String requestId = UUID.randomUUID().toString();

        return webClientPaymentProxy.createPayment(requestId, payment);
    }
}

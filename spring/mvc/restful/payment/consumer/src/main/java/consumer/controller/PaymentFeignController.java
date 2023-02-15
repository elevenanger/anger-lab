package consumer.controller;

import consumer.proxy.PaymentProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import provider.model.Payment;

import java.util.UUID;

/**
 * author : anger
 * date : 2022/7/24 17:57
 * description : 支付服务客户端 controller
 */
@Slf4j
@RestController
public class PaymentFeignController {

    private final PaymentProxy paymentProxy;

    public PaymentFeignController(PaymentProxy paymentProxy) {
        this.paymentProxy = paymentProxy;
    }

    @PostMapping("/payment")
    public Payment createPayment(
        @RequestBody Payment payment) {
        String requestId = UUID.randomUUID().toString();
        payment.setId(requestId);
        log.info("构造 feign 请求，requestId：{}，请求参数：{}", requestId, payment);
        return paymentProxy.createPayment(requestId, payment);
    }

}

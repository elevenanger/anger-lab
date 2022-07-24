package provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import provider.model.Payment;

/**
 * author : liuanglin
 * date : 2022/7/24 17:24
 * description : 支付请求 controller
 */
@Slf4j
@RestController
public class PaymentController {

    @PostMapping("/payment")
    public ResponseEntity<Payment> createPayment(
        @RequestHeader String requestId,
        @RequestBody Payment payment) {
        log.info("收到支付请求，ID：{}，请求报文：{}", requestId, payment);
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .header("requestId", requestId)
            .body(payment);
    }
}

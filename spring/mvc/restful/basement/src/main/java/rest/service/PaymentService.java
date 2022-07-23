package rest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rest.exception.NotEnoughMoneyException;
import rest.model.PaymentDetail;

/**
 * author : liuanglin
 * date : 2022/7/23 17:23
 * description : 支付服务
 */
@Slf4j
@Service
public class PaymentService {
    public PaymentDetail processPayment() {
        throw new NotEnoughMoneyException();
    }

    public PaymentDetail processPayment(PaymentDetail paymentDetail) {
        log.info("收到支付请求，{}", paymentDetail);
        double price = paymentDetail.getPrice();
        if (price <= 0)
            throw new NotEnoughMoneyException();
        paymentDetail.setPrice(price * 100);
        return paymentDetail;
    }
}

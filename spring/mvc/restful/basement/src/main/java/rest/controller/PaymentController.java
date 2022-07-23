package rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rest.exception.ErrorDetail;
import rest.exception.NotEnoughMoneyException;
import rest.model.PaymentDetail;
import rest.service.PaymentService;

/**
 * author : liuanglin
 * date : 2022/7/23 17:26
 * description : 支付 Controller
 */
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /*
    发起支付请求
    在方法内处理异常
     */
    @PostMapping("/payment")
    public ResponseEntity<?> makePayment() {
        try {
            /*
            调用支付服务
            调用成功返回结果
             */
            PaymentDetail paymentDetail =
                paymentService.processPayment();
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(paymentDetail);
        }catch (NotEnoughMoneyException e) {
            /*
            异常情况则进入异常处理逻辑
            设置 HTTP 请求结果错误码 400
            在 http body 返回错误信息
             */
            ErrorDetail errorDetail = new ErrorDetail();
            errorDetail.setMessage("余额不足");
            return ResponseEntity
                    .badRequest()
                    .body(errorDetail);
        }
    }

    /*
    分离业务逻辑与异常处理
    controller 只需要处理正常的业务逻辑
    异常情况由 ExceptionControllerAdvice 处理
     */
    @PostMapping("/order")
    public ResponseEntity<PaymentDetail> processPayment() {
        PaymentDetail paymentDetail =
            paymentService.processPayment();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(paymentDetail);
    }

    /*
    @RequestBody 默认 http body 中的请求参数为 JSON 格式
    并将 JSON String 转换为 @RequestBody 注解的参数类的实例
     */
    @PostMapping("/pay")
    public ResponseEntity<PaymentDetail> pay(
        @RequestBody PaymentDetail paymentDetail) {
        PaymentDetail paymentDetail1 =
            paymentService.processPayment(paymentDetail);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(paymentDetail1);
    }
}

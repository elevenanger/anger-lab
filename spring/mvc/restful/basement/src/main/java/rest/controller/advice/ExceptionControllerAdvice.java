package rest.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rest.exception.ErrorDetail;
import rest.exception.NotEnoughMoneyException;

/**
 * author : liuanglin
 * date : 2022/7/23 17:43
 * description : 处理异常流程
 * 注解 @RestControllerAdvice 标记该类为一个 Rest controller advice
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /*
    使用 @ExceptionHandler 处理指定异常
     */
    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorDetail> exceptionNotEnoughMoney() {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setMessage("余额不足");
        log.info("支付请求异常，{}", errorDetail);
        return ResponseEntity
                .badRequest()
                .body(errorDetail);
    }
}

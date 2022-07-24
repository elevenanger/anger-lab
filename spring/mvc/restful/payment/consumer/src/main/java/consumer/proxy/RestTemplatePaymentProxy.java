package consumer.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import provider.model.Payment;

import java.util.UUID;

/**
 * author : liuanglin
 * date : 2022/7/24 18:42
 * description : 使用 RestTemplate 消费 REST 服务
 */
@Slf4j
@Component
public class RestTemplatePaymentProxy {

    /*
    使用构造器注入注入 RestTemplate bean
     */
    private final RestTemplate restTemplate;

    @Value("${payment.service.url}")
    private String paymentServiceURL;

    public RestTemplatePaymentProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Payment createPayment(Payment payment) {
        String requestId = UUID.randomUUID().toString();
        payment.setId(requestId);
        String uri = paymentServiceURL + "/payment";
        /*
        创建 HTTP header
         */
        HttpHeaders headers = new HttpHeaders();
        headers.add("requestId", requestId);

        /*
        使用 HttpEntity 定义对象请求数据
         */
        HttpEntity<Payment> httpEntity = new HttpEntity<>(payment, headers);
        log.info("构造 rest template 请求，请求地址：{}，requestId：{}，请求参数：{}",
            uri, requestId, payment);

        /*
        使用 RestTemplate.exchange() 方法发送 HTTP 请求
        获取 http response 数据
         */
        ResponseEntity<Payment> response =
            restTemplate.exchange(uri,
                                HttpMethod.POST,
                                httpEntity,
                                Payment.class);
        return response.getBody();
    }
}

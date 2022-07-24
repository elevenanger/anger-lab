package consumer.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import provider.model.Payment;
import reactor.core.publisher.Mono;

/**
 * author : liuanglin
 * date : 2022/7/24 20:40
 * description : webclient
 */
@Slf4j
@Component
public class WebClientPaymentProxy {

    @Value("${payment.service.url}")
    private String requestUrl;
    private final WebClient webClient;

    public WebClientPaymentProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Payment> createPayment(String requestId,
                                        Payment payment) {
        payment.setId(requestId);
        log.info("构造 webclient 请求，requestId：{}，请求参数：{}", requestId, payment);
        return webClient.post()
            .uri(requestUrl + "/payment")
            .header("requestId", requestId)
            .body(Mono.just(payment), Payment.class)
            .retrieve()
            .bodyToMono(Payment.class);
    }
}

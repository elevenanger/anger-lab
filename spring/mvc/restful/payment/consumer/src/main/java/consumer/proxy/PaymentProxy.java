package consumer.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import provider.model.Payment;

/**
 * author : liuanglin
 * date : 2022/7/24 17:42
 * description : payment proxy
 * 定义 payment 接口
 * 在接口中声明需要消费的 REST 服务端点
 * 仅需要在方法上使用注解定义 URL HTTP 方法和参数
 * 不需要编码实现接口
 * Spring 会自动生成实现
 * OpenFeign 实现接口并在 Spring 上下文中定义实现 bean
 *
 * 使用注解 @FeignClient 配置 REST 客户端
 * 最基础的配置是定义名称和URL地址
 */
@FeignClient(name = "payment",
            url = "${payment.service.url}")
public interface PaymentProxy {

    /*
    指定端点地址和 HTTP 方法
    定义请求参数
     */
    @PostMapping("/payment")
    Payment createPayment(
        @RequestHeader String requestId,
        @RequestBody Payment payment);
}

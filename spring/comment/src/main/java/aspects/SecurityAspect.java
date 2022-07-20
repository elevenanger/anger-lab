package aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

/**
 * author : liuanglin
 * date : 2022/7/20 08:25
 * description : 安全相关切面
 * 通过 @Order 注解声明多个切面组成的调用链的执行顺序
 */
@Slf4j
@Aspect
@Order(2)
public class SecurityAspect {
    @Around("@annotation(annotations.ToLog)")
    public Object toLogSecurity(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("Around toLogSecurity 开始调用 @ToLog 注解服务:{}，参数：{}", methodName,  Arrays.asList(args));
        Object returnedByMethod = joinPoint.proceed();
        log.info("@ToLog toLogSecurity 注解服务调用结束，返回数据：{}",returnedByMethod);
        return returnedByMethod;
    }
}

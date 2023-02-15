package aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

/**
 * author : anger
 * date : 2022/7/19 17:05
 * description : 日志切面
 * 注解 @Aspect 声明面向切片编程相关逻辑
 * 使用 @Bean 或者原型注解将该类添加到 Spring 上下文中
 */
@Order(1)
@Aspect
@Slf4j
public class LoggingAspect {

    /*
    @Around 注解告诉 Spring 何时介入那些方法调用
     */
    @Around("execution(* service.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("开始调用服务:{}，参数：{}", methodName,  Arrays.asList(args));
        Object returnedByMethod = joinPoint.proceed();
        log.info("服务调用结束，返回数据：{}",returnedByMethod);
        return returnedByMethod;
    }

    /*
    通过切面干涉注解方法
     */
    @Around("@annotation(annotations.ToLog)")
    public Object toLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("Around toLogAround 开始调用 @ToLog 注解服务:{}，参数：{}", methodName,  Arrays.asList(args));
        Object returnedByMethod = joinPoint.proceed();
        log.info("@ToLog toLogAround 注解服务调用结束，返回数据：{}",returnedByMethod);
        return returnedByMethod;
    }

    /*
    在正式调用干涉方法之前调用切面逻辑方法
     */
    @Before("@annotation(annotations.ToLog)")
    public void toLogBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("Before 调用 @ToLog 注解服务:{}，参数：{}", methodName,  Arrays.asList(args));
    }

    /*
    @AfterReturning 注解声明的逻辑在方法执行完之后可以获取到方法的返回结果
    并执行相应的逻辑
    returning = "returnedValue" 定义方法的返回结果
     */
    @AfterReturning(value = "@annotation(annotations.ToLog)",
                    returning = "returnedValue")
    public void toLogAfterReturning(Object returnedValue) {
        log.info("AfterReturning @ToLog 注解方法执行结束，返回值：{}", returnedValue);
    }
}

package aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

/**
 * author : liuanglin
 * date : 2022/7/19 17:05
 * description : 日志切面
 * 注解 @Aspect 声明面向切片编程相关逻辑
 * 使用 @Bean 或者原型注解将该类添加到 Spring 上下文中
 */
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
    public Object toLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("开始调用 @ToLog 注解服务:{}，参数：{}", methodName,  Arrays.asList(args));
        Object returnedByMethod = joinPoint.proceed();
        log.info("@ToLog 注解服务调用结束，返回数据：{}",returnedByMethod);
        return returnedByMethod;
    }
}

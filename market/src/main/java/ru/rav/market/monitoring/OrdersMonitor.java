package ru.rav.market.monitoring;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class OrdersMonitor {

    public Map<String, Integer> counts;
    public Map<String, Long> time;

    @PostConstruct
    public void init() {
        this.counts = new HashMap<>();
        this.time = new HashMap<>();
    }

    @Before("execution(public * ru.rav.market.order.OrderController.*(..))")
    public void countOrderControllerMethodCalls(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.toShortString();

        Integer num = 1;
        if (counts.containsKey(methodName)) {
            num = counts.get(methodName) + 1;
        }
        counts.put(methodName, num);

        log.info(counts.toString());
    }

    @Around("execution(public * ru.rav.market.products.ProductController.*(..))")
    public Object measureTimeForProductsControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.toShortString();

        long startTime = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        long ms = duration;
        if (time.containsKey(methodName)) {
            ms = time.get(methodName) + duration;
        }
        time.put(methodName, ms);

        log.info(time.toString());
        return out;
    }


}

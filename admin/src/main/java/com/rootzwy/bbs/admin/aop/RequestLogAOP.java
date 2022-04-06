package com.rootzwy.bbs.admin.aop;

import com.rootzwy.bbs.admin.context.AdminHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zwy
 * @date 2022/3/21
 */
@Aspect
@Component
public class RequestLogAOP {

    private final Logger logger = LoggerFactory.getLogger(RequestLogAOP.class);

    @Pointcut("@annotation(com.rootzwy.bbs.admin.annotation.WriteLog)")
    public void RequestLogPointCut() {

    }

    @Around("RequestLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // TODO 魔数提取
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 判断是否是 Web 请求，如果不是直接跳过
        if (attributes == null) {
            return joinPoint.proceed(joinPoint.getArgs());
        }
        HttpServletRequest request = attributes.getRequest();
        String url = request.getServletPath();
        Long userId = AdminHolder.getOrEmpty().getId();
        String requestId = request.getHeader("x-request-id");
        logger.info("userId: {}, requestPath: {}", userId, url);
        long startTimeMillis = System.currentTimeMillis();
        Object obj = joinPoint.proceed(joinPoint.getArgs());
        long totalTime = System.currentTimeMillis() - startTimeMillis;
        logger.info("请求执行成功，执行信息：request-id：{}，执行时长：{} ms", requestId, totalTime);
        return obj;
    }

}

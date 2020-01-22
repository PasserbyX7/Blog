package cn.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * LogAspect
 */
@Aspect
@Component
public class LogAspect {

    //配置切面表达式
    @Pointcut("execution(* cn.controller.*.*(..))")
    public void log(){}
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest req=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String url=req.getRequestURI().toString();
        String ip=req.getRemoteAddr();
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url, ip, classMethod, args);
        logger.info("request : {}",requestLog);
    }
    @AfterReturning(pointcut = "log()",returning = "result")
    public void doAfterReturning(Object result) {
        logger.info("Result : {}", result);
    }
    private Logger logger=LoggerFactory.getLogger(this.getClass());
}
@Data
@AllArgsConstructor
class RequestLog{
    private String url;
    private String ip;
    private String classMethod;
    private Object[] args;
}
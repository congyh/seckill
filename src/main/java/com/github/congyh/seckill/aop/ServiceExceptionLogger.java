package com.github.congyh.seckill.aop;

import com.github.congyh.seckill.exception.DAOException;
import com.github.congyh.seckill.util.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 统一对DAO层, Service层的异常进行记录
 *
 * <p>注意: 这里只是对异常处理的增强, 通常只是记录日志, 异常还会继续向上抛出. 所以还需要在web层做一个最终的捕获处理和展示.
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Aspect
@Component
public class ServiceExceptionLogger {

    private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionLogger.class);

    @Pointcut("execution(public * com.github.congyh.seckill.service.*.*(..))")
    public void log() {}

    /**
     * 记录错误日志
     *
     * @param joinPoint 方法连接点
     * @param exception 封装后的异常类型
     */
    @AfterThrowing(pointcut = "log()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.error("-----{}.{}()-----", className, methodName);
        logger.error("调用参数: {}", Arrays.toString(args));
        if (exception instanceof DAOException) {
            logger.error("exception message: {}", ExceptionUtils.toString(exception.getCause()));
        } else {
            logger.error("exception message: {}", ExceptionUtils.toString(exception));
        }
    }
}

package com.github.congyh.seckill.advice;

import com.github.congyh.seckill.exception.ServiceException;
import com.github.congyh.seckill.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一Controller层异常处理
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 统一处理SeckillException类型
     *
     * <p>TODO 后期异常类型增多的时候, 可以拆分此方法</p>
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public Result handleSeckillException(ServiceException e) {
        logger.error("[秒杀异常] {} ", e);
//        return ResultUtils.
        // TODO 待实现.
        return null;
    }

}

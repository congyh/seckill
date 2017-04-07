package com.github.congyh.seckill.aop;

import com.github.congyh.seckill.dto.Result;
import com.github.congyh.seckill.enums.ResultTypeEnum;
import com.github.congyh.seckill.exception.DAOException;
import com.github.congyh.seckill.exception.DomainNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Web层统一异常处理
 *
 * <p>以json形式返回给网页, 给出友好的错误信息</p>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(DomainNotFoundException.class)
    @ResponseBody
    public Result handleDomainNotFoundException(DomainNotFoundException e) {
        return new Result(ResultTypeEnum.DOMAIN_NOT_FOUND);
    }

    @ExceptionHandler(DAOException.class)
    @ResponseBody
    public Result handleDAOException(DAOException e) {
        return new Result(ResultTypeEnum.UNKNOWN_ERROR);
    }
}

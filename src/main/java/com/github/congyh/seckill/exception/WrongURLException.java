package com.github.congyh.seckill.exception;

/**
 * 秒杀地址错误异常
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class WrongURLException extends SeckillException {

    public WrongURLException(String message) {
        super(message);
    }

    public WrongURLException(String message, Throwable cause) {
        super(message, cause);
    }
}

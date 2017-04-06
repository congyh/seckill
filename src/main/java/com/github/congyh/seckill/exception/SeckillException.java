package com.github.congyh.seckill.exception;

/**
 * 秒杀异常基类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }
}

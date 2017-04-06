package com.github.congyh.seckill.exception;

/**
 * 秒杀结束异常
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillEndException extends SeckillException {
    public SeckillEndException() {
        this("该商品的秒杀已经结束!");
    }

    public SeckillEndException(String message) {
        super(message);
    }
}

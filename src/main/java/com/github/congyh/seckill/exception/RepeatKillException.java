package com.github.congyh.seckill.exception;

/**
 * 重复秒杀异常
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException() {
        this("您已成功秒杀, 无法重复秒杀!");
    }

    public RepeatKillException(String message) {
        super(message);
    }

}

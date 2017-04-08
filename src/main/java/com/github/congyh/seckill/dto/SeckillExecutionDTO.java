package com.github.congyh.seckill.dto;

import java.io.Serializable;

/**
 * 秒杀操作结果封装类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillExecutionDTO implements Serializable {

    private static final long serialVersionUID = 5963307718372500211L;
    private Long seckillProductId;
    private Long userPhone;

    public SeckillExecutionDTO(long seckillProductId, long userPhone) {
    }

    public Long getSeckillProductId() {
        return seckillProductId;
    }

    public void setSeckillProductId(Long seckillProductId) {
        this.seckillProductId = seckillProductId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }
}

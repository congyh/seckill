package com.github.congyh.seckill.entity;

import java.util.Date;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SuccessKilled {
    /** 多对一对应关系, 在多的一方记录下唯一实体, 方便后续操作 */
    private Seckill seckill;
    private Long userPhone;
    private Short state;
    private Date createTime;

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
            "seckill=" + seckill +
            ", userPhone=" + userPhone +
            ", state=" + state +
            ", createTime=" + createTime +
            '}';
    }
}

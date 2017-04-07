package com.github.congyh.seckill.domain;

import java.util.Date;

/**
 * 订单
 *
 * <p>秒杀成功后, 会生成订单</p>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillOrderDO {
    private Long id;
    /** 多对一对应关系, 在多的一方记录下唯一实体, 方便后续操作 */
    private Long seckillProductDOId;
    private Long userPhone;
    private Short orderStatus;
    private Date createTime;

    @Override
    public String toString() {
        return "SeckillOrderDO{" +
            "id=" + id +
            ", seckillProductDOId=" + seckillProductDOId +
            ", userPhone=" + userPhone +
            ", orderStatus=" + orderStatus +
            ", createTime=" + createTime +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeckillProductDOId() {
        return seckillProductDOId;
    }

    public void setSeckillProductDOId(Long seckillProductDOId) {
        this.seckillProductDOId = seckillProductDOId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

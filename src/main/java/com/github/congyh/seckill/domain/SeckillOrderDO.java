package com.github.congyh.seckill.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 *
 * <p>秒杀成功后, 会生成订单</p>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillOrderDO implements Serializable {

    private static final long serialVersionUID = -7938471904858934444L;
    private Long id;
    /** 多对一对应关系, 在多的一方记录下唯一实体, 方便后续操作 */
    private Long seckillProductId;
    private Long userPhone;
    private Short orderStatus;
    private Date gmtCreate;
    private Date gmtModified;

    @Override
    public String toString() {
        return "SeckillOrderDO{" +
            "id=" + id +
            ", seckillProductId=" + seckillProductId +
            ", userPhone=" + userPhone +
            ", orderStatus=" + orderStatus +
            ", gmtCreate=" + gmtCreate +
            ", gmtModified=" + gmtModified +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}

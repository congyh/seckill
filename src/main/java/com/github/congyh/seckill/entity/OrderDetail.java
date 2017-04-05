package com.github.congyh.seckill.entity;

import java.util.Date;

/**
 * 订单
 *
 * <p>秒杀成功后, 会生成订单</p>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class OrderDetail {
    /** 多对一对应关系, 在多的一方记录下唯一实体, 方便后续操作 */
    private Product product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
            "product=" + product +
            ", userPhone=" + userPhone +
            ", state=" + state +
            ", createTime=" + createTime +
            '}';
    }
}

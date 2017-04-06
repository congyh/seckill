package com.github.congyh.seckill.domain;

import java.util.Date;

/**
 * 订单
 *
 * <p>秒杀成功后, 会生成订单</p>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class OrderDetailDO {
    private Long id;
    /** 多对一对应关系, 在多的一方记录下唯一实体, 方便后续操作 */
    private ProductDO productDO;
    private Long userPhone;
    private Short state;
    private Date createTime;

    @Override
    public String toString() {
        return "OrderDetailDO{" +
            "productDO=" + productDO +
            ", id=" + id +
            ", userPhone=" + userPhone +
            ", state=" + state +
            ", createTime=" + createTime +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ProductDO getProductDO() {
        return productDO;
    }

    public void setProductDO(ProductDO productDO) {
        this.productDO = productDO;
    }

}

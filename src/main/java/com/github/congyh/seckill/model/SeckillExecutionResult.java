package com.github.congyh.seckill.model;

import com.github.congyh.seckill.enums.SeckillExecutionStatus;

/**
 * 秒杀操作结果封装类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillExecutionResult {

    private long productId;
    private long userPhone;

    // TODO 能够将下面两个字段封装为枚举
    // 现在这样写是因为用枚举的话传输可能会有问题.
    /** 秒杀结果执行状态 */
    private int status;
    private String statusInfo;
//    /** 秒杀成功对象 */
//    private OrderDetail successKilled;

    public SeckillExecutionResult(long productId,
                                  long userPhone,
                                  SeckillExecutionStatus seckillExecutionStatus) {
        this.productId = productId;
        this.userPhone = userPhone;
        this.status = seckillExecutionStatus.getStatus();
        this.statusInfo = seckillExecutionStatus.getStatusInfo();
    }

    /**
     * 秒杀失败
     *
     * @param productId 秒杀id
     * @param seckillExecutionStatus 秒杀结果状态枚举
     */
    public SeckillExecutionResult(long productId,
                                  SeckillExecutionStatus seckillExecutionStatus) {
        this.productId = productId;
        this.status = status;
        this.statusInfo = statusInfo;
    }

    @Override
    public String toString() {
        return "SeckillExecutionResult{" +
            "productId=" + productId +
            ", userPhone=" + userPhone +
            ", status=" + status +
            ", statusInfo='" + statusInfo + '\'' +
            '}';
    }

    public long getSeckillId() {
        return productId;
    }

    public void setSeckillId(long productId) {
        this.productId = productId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

//    public OrderDetail getOrder() {
//        return successKilled;
//    }
//
//    public void setOrder(OrderDetail successKilled) {
//        this.successKilled = successKilled;
//    }
}

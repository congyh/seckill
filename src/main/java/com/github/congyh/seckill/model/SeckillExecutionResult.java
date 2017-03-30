package com.github.congyh.seckill.model;

import com.github.congyh.seckill.entity.SuccessKilled;

/**
 * 秒杀操作结果封装类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillExecutionResult {

    private Long seckillId;
    /** 秒杀结果执行状态 */
    private Integer status;
    private String statusInfo;
    /** 秒杀成功对象 */
    private SuccessKilled successKilled;

    public SeckillExecutionResult(Long seckillId, Integer status,
                                  String statusInfo, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.status = status;
        this.statusInfo = statusInfo;
        this.successKilled = successKilled;
    }

    /**
     * 失败状态
     *
     * @param seckillId 秒杀id
     * @param status 秒杀结果状态
     * @param statusInfo 秒杀结果详情
     */
    public SeckillExecutionResult(Long seckillId, Integer status, String statusInfo) {
        this.seckillId = seckillId;
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}

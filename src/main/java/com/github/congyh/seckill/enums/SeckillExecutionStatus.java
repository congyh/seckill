package com.github.congyh.seckill.enums;

/**
 * 秒杀状态枚举
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public enum SeckillExecutionStatus {
    SUCCESS(1, "秒杀成功"), END(0, "秒杀结束"),
    REPEAT(-1, "重复秒杀"), UNKNOWN(-2, "未知异常");

    private int status;
    private String statusInfo;

    SeckillExecutionStatus(int status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    /**
     * 根据秒杀状态获取枚举对象
     *
     * @param status 秒杀状态
     * @return 秒杀状态枚举
     */
    public static SeckillExecutionStatus statusOf(int status) {
        for (SeckillExecutionStatus seckillExecutionStatus : values()) {
            if (seckillExecutionStatus.getStatus() == status) {
                return seckillExecutionStatus;
            }
        }
        return null;
    }
}

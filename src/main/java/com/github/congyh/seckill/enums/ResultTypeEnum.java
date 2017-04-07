package com.github.congyh.seckill.enums;

/**
 * Json响应状态枚举
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public enum ResultTypeEnum {

    UNKNOWN_ERROR(-1, "未知错误"),
    OK(0, "OK"),
    SECKILL_SUCCESS(1, "秒杀成功"),
    SECKILL_END(2, "秒杀结束"),
    SECKILL_REPEAT(3, "重复秒杀"),
    WRONG_URL(4, "秒杀地址错误"),
    DOMAIN_NOT_FOUND(5, "商品实体未找到");

    private int code;
    private String message;

    ResultTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据code值返回对应的枚举对象
     *
     * @param code code
     * @return 枚举对象
     */
    public static ResultTypeEnum codeOf(int code) {
        for (ResultTypeEnum resultTypeEnum : values()) {
            if (resultTypeEnum.getCode() == code) {
                return resultTypeEnum;
            }
        }

        return null;
    }
}

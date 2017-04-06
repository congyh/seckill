package com.github.congyh.seckill.enums;

/**
 * Json响应状态枚举
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public enum JsonResponseCode {

    SUCEESS(0, "OK"), ERROR(-1, "ERROR");

    private int coce;
    private String message;

    JsonResponseCode(int coce, String message) {
        this.coce = coce;
        this.message = message;
    }

    public int getCoce() {
        return coce;
    }

    public void setCoce(int coce) {
        this.coce = coce;
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
    public static JsonResponseCode codeOf(int code) {
        for (JsonResponseCode jsonResponseCode : values()) {
            if (jsonResponseCode.getCoce() == code) {
                return jsonResponseCode;
            }
        }

        return null;
    }
}

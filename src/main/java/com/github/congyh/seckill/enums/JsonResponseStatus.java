package com.github.congyh.seckill.enums;

/**
 * Json响应状态枚举
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public enum JsonResponseStatus {

    SUCEESS(0, "OK"), ERROR(-1, "ERROR");

    private int status;
    private String message;

    JsonResponseStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据status值返回对应的枚举对象
     *
     * @param status status值
     * @return 枚举对象
     */
    public static JsonResponseStatus stateOf(int status) {
        for (JsonResponseStatus jsonResponseStatus: values()) {
            if (jsonResponseStatus.getStatus() == status) {
                return jsonResponseStatus;
            }
        }

        return null;
    }
}

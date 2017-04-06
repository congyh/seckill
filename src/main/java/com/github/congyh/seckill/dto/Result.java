package com.github.congyh.seckill.dto;

/**
 * 封装所有Json类型返回信息
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class Result<T> {

    /** 错误码 */
    public int code;
    /** 提示信息 */
    private String msg;
    /** 返回数据 */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

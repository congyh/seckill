package com.github.congyh.seckill.dto;

import com.github.congyh.seckill.enums.ResultTypeEnum;

/**
 * 封装所有Json类型返回信息
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class Result<T> {

    /** 错误码 */
    public Integer code;
    /** 提示信息 */
    private String msg;
    /** 返回数据 */
    private T data;

    public Result(ResultTypeEnum resultTypeEnum, T data) {
        this.code = resultTypeEnum.getCode();
        this.msg = resultTypeEnum.getMessage();
        this.data = data;
    }

    public Result(ResultTypeEnum resultTypeEnum) {
        this.code = resultTypeEnum.getCode();
        this.msg = resultTypeEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

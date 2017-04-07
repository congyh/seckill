package com.github.congyh.seckill.dto;

import com.github.congyh.seckill.enums.ResultTypeEnum;

/**
 * 封装所有返回类结果信息
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class Result<T> {

    /** 错误码 */
    public Integer code;
    /** 提示信息 */
    private String message;
    /** 返回数据 */
    private T data;

    public Result(ResultTypeEnum resultTypeEnum, T data) {
        this.code = resultTypeEnum.getCode();
        this.message = resultTypeEnum.getMessage();
        this.data = data;
    }

    public Result(ResultTypeEnum resultTypeEnum) {
        this.code = resultTypeEnum.getCode();
        this.message = resultTypeEnum.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

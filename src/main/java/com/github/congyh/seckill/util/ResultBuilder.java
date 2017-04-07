package com.github.congyh.seckill.util;

import com.github.congyh.seckill.enums.ResultTypeEnum;

/**
 * 统一封装结果
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Deprecated
public class ResultBuilder<T> {
    private Integer code;
    private String msg;
    private T data;

    /**
     * 添加结果类型
     *
     * @param resultTypeEnum 结果类型枚举
     * @return result对象
     */
    public ResultBuilder withType(ResultTypeEnum resultTypeEnum) {
        this.code = resultTypeEnum.getCode();
        this.msg = resultTypeEnum.getMessage();
        return this;
    }

//    public <T> Result<T> build() {
//        return new Result<T>()
//    }
}

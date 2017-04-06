package com.github.congyh.seckill.util;

import com.github.congyh.seckill.enums.ResultStatusEnum;
import com.github.congyh.seckill.dto.Result;

/**
 * 生成JsonResponse响应
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public final class ResultUtils {

    /**
     * 返回成功状态的Json响应
     *
     * @param data 响应数据
     * @param <T> 数据类型
     * @return 成功状态的Json串
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(ResultStatusEnum.SUCEESS.getCoce());
        result.setData(data);
        result.setMsg(ResultStatusEnum.SUCEESS.getMessage());

        return result;
    }

    public static Result withCode(ResultStatusEnum code) {
        // TODO 还没有实现
        return null;
    }
}

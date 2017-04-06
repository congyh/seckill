package com.github.congyh.seckill.utils;

import com.github.congyh.seckill.enums.JsonResponseCode;
import com.github.congyh.seckill.model.JsonResponse;

/**
 * 生成JsonResponse响应
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public final class JsonResponseBuilder {

    /**
     * 返回成功状态的Json响应
     *
     * @param data 响应数据
     * @param <T> 数据类型
     * @return 成功状态的Json串
     */
    public static <T> JsonResponse<T> success(T data) {
        JsonResponse<T> jsonResponse = new JsonResponse<T>();
        jsonResponse.setCode(JsonResponseCode.SUCEESS.getCoce());
        jsonResponse.setData(data);
        jsonResponse.setMsg(JsonResponseCode.SUCEESS.getMessage());

        return jsonResponse;
    }

    public static JsonResponse withCode(JsonResponseCode code) {
        // TODO 还没有实现
        return null;
    }
}

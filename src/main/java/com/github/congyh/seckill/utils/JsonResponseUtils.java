package com.github.congyh.seckill.utils;

import com.github.congyh.seckill.enums.JsonResponseStatus;
import com.github.congyh.seckill.model.JsonResponse;

/**
 * 生成JsonResponse响应
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public final class JsonResponseUtils {

    /**
     * 返回成功状态的Json响应
     *
     * @param data 响应数据
     * @param <T> 数据类型
     * @return 成功状态的Json串
     */
    public static <T> JsonResponse<T> success(T data) {
        JsonResponse<T> jsonResponse = new JsonResponse<T>();
        jsonResponse.setCode(JsonResponseStatus.SUCEESS.getStatus());
        jsonResponse.setData(data);
        jsonResponse.setMsg(JsonResponseStatus.SUCEESS.getMessage());

        return jsonResponse;
    }
}

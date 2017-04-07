package com.github.congyh.seckill.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常处理工具
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public final class ExceptionUtils {

    private ExceptionUtils() {}

    /**
     * 异常堆栈信息转字符串
     *
     * @param e
     * @return string
     */
    public static String toString(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}

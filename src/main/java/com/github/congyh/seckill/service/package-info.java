/**
 * Service层
 *
 * <pre>
 * 1. Service层方法允许返回null, 需要在web层手动进行NPE判断, 抛出和统一处理异常
 * 2. 由于Mybatis只有接口, 不手写实现类, 并且DAO层异常繁杂且通常无法恢复, 所以对DAO层的异常统一在Service层捕捉为Exception类型,
 * 并重新抛出为通用的DAOException, 同时在throwable参数中记录原始异常类型, 异常发生点等. 注意: stacktrace可以不打印到日志中, 因为通常
 * 不可恢复, 即使打印了也没什么帮助.
 * </pre>
 *
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
package com.github.congyh.seckill.service;
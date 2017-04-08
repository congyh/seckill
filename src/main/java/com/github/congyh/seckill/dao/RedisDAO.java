package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.domain.SeckillProductDO;

/**
 * 由于使用了Spring内置的AOP缓存方式, 不再使用手动序列化方式
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Deprecated
public interface RedisDAO {

    /**
     * 从Redis中读取对象
     *
     * @param productId 对象id
     * @return 对象
     */
    SeckillProductDO getProduct(long productId);

    /**
     * 向Redis中写入对象
     *
     * @param seckillProductDO 待写入的对象
     * @return 写入状态, 如果正确是OK, 如果错误是错误信息字符串
     */
    String setProduct(SeckillProductDO seckillProductDO);
}

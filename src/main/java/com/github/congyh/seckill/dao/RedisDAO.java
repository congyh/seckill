package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.domain.SeckillProductDO;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
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

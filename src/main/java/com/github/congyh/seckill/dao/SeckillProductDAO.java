package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.domain.SeckillProductDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Mapper
public interface SeckillProductDAO {

    /**
     * 减库存
     *
     * @param id 秒杀商品id
     * @param gmtKill 秒杀时间
     * @return 更新语句影响的行数, 如果是0的话, 说明没有执行成功.
     */
    int reduceNumber(@Param("id") long id,
                     @Param("gmtKill") Date gmtKill);

    /**
     * 查询单个商品详情
     *
     * @param id 秒杀商品id
     * @return 单个商品详情
     */
    SeckillProductDO findById(@Param("id") long id);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offset 表偏移量
     * @param limit 结果行数
     * @return 秒杀商品列表
     */
    List<SeckillProductDO> findAll(@Param("offset") int offset,
                                   @Param("limit") int limit);
}

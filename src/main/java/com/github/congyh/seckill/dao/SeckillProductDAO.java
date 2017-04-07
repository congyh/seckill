package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.domain.SeckillProductDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Mapper
public interface SeckillProductDAO {
    /** 减库存 */
    String REDUCE_NUMBER = "" +
        "update seckill_product " +
        "set number = number - 1 " +
        "where id = #{id} " +
        "and start_time <= #{killTime} " +
        "and end_time >= #{killTime} " +
        "and number > 0";

    /** 查询库存*/
    String FIND_BY_ID = "" +
        "select * from seckill_product " +
        "where id = #{id}";

    /** 查询全部库存 */
    String FIND_ALL = "" +
        "select * from seckill_product " +
        "order by create_time desc " +
        "limit #{offset}, #{limit}";

    /**
     * 减库存
     *
     * @param id 秒杀商品id
     * @param killTime 秒杀时间
     * @return 更新语句影响的行数, 如果是0的话, 说明没有执行成功.
     */
    @Update(REDUCE_NUMBER)
    int reduceNumber(@Param("id") long id,
                     @Param("killTime") Date killTime);

    /**
     * 查询单个商品详情
     *
     * @param id 秒杀商品id
     * @return 单个商品详情
     */
    @Select(FIND_BY_ID)
    SeckillProductDO findById(@Param("id") long id);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offset 表偏移量
     * @param limit 结果行数
     * @return 秒杀商品列表
     */
    @Select(FIND_ALL)
    List<SeckillProductDO> findAll(@Param("offset") int offset,
                                   @Param("limit") int limit);
}

package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * seckill表Mapper
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Mapper
public interface SeckillMapper {
    /** 减库存 */
    String REDUCE_NUMBER = "" +
        "update seckill " +
        "set number = number - 1 " +
        "where seckill_id = #{seckillId} " +
        "and start_time <= #{killTime} " +
        "and end_time >= #{killTime} " +
        "and number > 0";

    /** 查询库存*/
    String FIND_BY_ID = "" +
        "select * from seckill " +
        "where seckill_id = #{seckillId}";

    /** 查询全部库存 */
    String FIND_ALL = "" +
        "select * from seckill " +
        "order by create_time desc " +
        "limit #{offset}, #{limit}";

    /**
     * 减库存
     *
     * @param seckillId 秒杀商品id
     * @param killTime 秒杀时间
     * @return 更新语句影响的行数, 如果是0的话, 说明没有执行成功.
     */
    @Update(REDUCE_NUMBER)
    int reduceNumber(@Param("seckillId") long seckillId,
                     @Param("killTime") Date killTime);

    /**
     * 查询库存
     *
     * @param seckillId 秒杀商品id
     * @return
     */
    @Select(FIND_BY_ID)
    Seckill findById(@Param("seckillId") long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offset 表偏移量
     * @param limit 结果行数
     * @return 秒杀商品列表
     */
    @Select(FIND_ALL)
    List<Seckill> findAll(@Param("offset") int offset,
                          @Param("limit") int limit);
}

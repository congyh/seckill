package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.entity.Seckill;
import com.github.congyh.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.*;

/**
 * success_killed表mapper
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Mapper
public interface SuccessKilledMapper {

    /** 插入秒杀成功明细 */
    String SAVE = "" +
        "insert ignore into " +
        "success_killed(seckill_id, user_phone, state) " +
        "values(#{seckillId}, #{userPhone}, 1)";

    /** 根据id查询SuccessKilled对象 */
    String FIND_BY_ID_AND_PHONE = "" +
        "select * from success_killed " +
        "where seckill_id = #{seckillId} " +
        "and user_phone = #{userPhone}";

    /**
     * 插入秒杀成功明细
     *
     * <p>可过滤重复秒杀,
     * 使用insert ignore操作的时候, 即使是没有插入进去, 也会成功返回,
     * 不会报sql错误, 会返回0, 我们就知道没有操作成功, 便于我们后期的操作</p>
     *
     * @param seckillId 秒杀商品id
     * @param userPhone 用户手机号
     * @return 保存操作影响的行数, 如果是0代表插入失败
     */
    @Insert(SAVE)
    int save(@Param("seckillId") long seckillId,
             @Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled对象
     *
     * @param seckillId 秒杀商品id
     * @param userPhone 用户手机号
     * @return 唯一的秒杀订单
     */
    @Select(FIND_BY_ID_AND_PHONE)
    @Results(value = {
        @Result(
            property = "seckill",
            column = "seckill_id",
            javaType = Seckill.class,
            one = @One(select = "com.github.congyh.seckill.dao.SeckillMapper.findById")),
        @Result(property = "userPhone", column = "user_phone"),
        @Result(property = "state", column = "state"),
        @Result(property = "createTime", column = "create_time")
    })
    SuccessKilled findByIdAndPhone(@Param("seckillId") long seckillId,
                                   @Param("userPhone") long userPhone);

}

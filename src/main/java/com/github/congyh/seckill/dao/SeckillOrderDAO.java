package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.domain.SeckillOrderDO;
import org.apache.ibatis.annotations.*;

/**
 * TODO 转为xml配置方式
 * 1. 加入resultMap映射, 包括返回值类型映射;
 * 2. select, update要指明所有列名, 不用*, 减少数据库解析压力, 减少数据传输和不必要的更新.
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Mapper
public interface SeckillOrderDAO {

    /** 秒杀成功, 生成订单 */
    // TODO modifiedtime
    String SAVE = "" +
        "insert ignore into " +
        "seckill_order(id, seckill_product_id, user_phone, order_status) " +
        "values(#{id}, #{seckillProductId}, #{userPhone}, 0)";

    /** 根据id查询订单对象 */
    String FIND_BY_ID_AND_PHONE = "" +
        "select * from seckill_order " +
        "where seckill_product_id = #{seckillProductId} " +
        "and user_phone = #{userPhone}";

    /**
     * 生成订单
     *
     * <p>可过滤重复秒杀,
     * 使用insert ignore操作的时候, 即使是没有插入进去, 也会成功返回,
     * 不会报sql错误, 会返回0, 我们就知道没有操作成功, 便于我们后期的操作</p>
     *
     * @param seckillProductId 秒杀商品id
     * @param userPhone 用户手机号
     * @return 保存操作影响的行数, 如果是0代表插入失败
     */
    @Insert(SAVE)
    int save(@Param("id") Long id,
             @Param("seckillProductId") long seckillProductId,
             @Param("userPhone") long userPhone);

    /**
     * 根据id查询Order对象
     *
     * @param seckillProductId 秒杀商品id
     * @param userPhone 用户手机号
     * @return 唯一的秒杀订单
     */
//    @Select(FIND_BY_ID_AND_PHONE)
//    @Results(value = {
//        @Result(property = "id", column = "id"),
//        @Result(
//            property = "productDO",
//            column = "product_id",
//            javaType = SeckillProductDO.class,
//            one = @One(select = "com.github.congyh.seckill.dao.SeckillProductDAO.findById")),
//        @Result(property = "userPhone", column = "user_phone"),
//        @Result(property = "state", column = "state"),
//        @Result(property = "createTime", column = "create_time")
//    })
    @Select(FIND_BY_ID_AND_PHONE)
    @Results(value = {
        @Result(property = "seckillProductId", column = "seckill_product_id"),
        @Result(property = "userPhone", column = "user_phone"),
        @Result(property = "orderStatus", column = "order_status"),
        @Result(property = "createTime", column = "create_time")
    })
    SeckillOrderDO findByIdAndPhone(@Param("seckillProductDOId") long seckillProductId,
                                    @Param("userPhone") long userPhone);

}

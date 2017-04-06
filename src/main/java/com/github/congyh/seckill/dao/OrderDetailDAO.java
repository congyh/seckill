package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.domain.OrderDetailDO;
import com.github.congyh.seckill.domain.ProductDO;
import org.apache.ibatis.annotations.*;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Mapper
public interface OrderDetailDAO {

    /** 秒杀成功, 生成订单 */
    String SAVE = "" +
        "insert ignore into " +
        "seckill_order(id, product_id, user_phone, state) " +
        "values(#{id}, #{productId}, #{userPhone}, 1)";

    /** 根据id查询订单对象 */
    String FIND_BY_ID_AND_PHONE = "" +
        "select * from seckill_order " +
        "where product_id = #{productId} " +
        "and user_phone = #{userPhone}";

    /**
     * 生成订单
     *
     * <p>可过滤重复秒杀,
     * 使用insert ignore操作的时候, 即使是没有插入进去, 也会成功返回,
     * 不会报sql错误, 会返回0, 我们就知道没有操作成功, 便于我们后期的操作</p>
     *
     * @param productId 秒杀商品id
     * @param userPhone 用户手机号
     * @return 保存操作影响的行数, 如果是0代表插入失败
     */
    @Insert(SAVE)
    int save(@Param("id") Long id,
             @Param("productId") long productId,
             @Param("userPhone") long userPhone);

    /**
     * 根据id查询Order对象
     *
     * @param productId 秒杀商品id
     * @param userPhone 用户手机号
     * @return 唯一的秒杀订单
     */
    @Select(FIND_BY_ID_AND_PHONE)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(
            property = "productDO",
            column = "product_id",
            javaType = ProductDO.class,
            one = @One(select = "com.github.congyh.seckill.dao.ProductDAO.findById")),
        @Result(property = "userPhone", column = "user_phone"),
        @Result(property = "state", column = "state"),
        @Result(property = "createTime", column = "create_time")
    })
    OrderDetailDO findByIdAndPhone(@Param("productId") long productId,
                                   @Param("userPhone") long userPhone);

}

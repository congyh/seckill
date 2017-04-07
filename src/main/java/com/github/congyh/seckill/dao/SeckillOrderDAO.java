package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.domain.SeckillOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Mapper
public interface SeckillOrderDAO {

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
    int save(@Param("seckillProductId") long seckillProductId,
             @Param("userPhone") long userPhone);

    /**
     * 根据id查询Order对象
     *
     * @param seckillProductId 秒杀商品id
     * @param userPhone 用户手机号
     * @return 唯一的秒杀订单
     */
    SeckillOrderDO findByIdAndPhone(@Param("seckillProductId") long seckillProductId,
                                    @Param("userPhone") long userPhone);
}

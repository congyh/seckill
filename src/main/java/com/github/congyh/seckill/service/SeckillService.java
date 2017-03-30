package com.github.congyh.seckill.service;

import com.github.congyh.seckill.entity.Seckill;
import com.github.congyh.seckill.model.SeckillURL;

import java.util.List;

/**
 * 秒杀服务接口
 * 
 * <pre>注意: 服务接口设计原则是站在终端用户的角度去设计,也就是说按照实际的业务行为来设计,
 * 而不是用抽象的逻辑操作来命名, 或者考虑参数.</pre>
 * 
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public interface SeckillService {

    List<Seckill> findAll();

    Seckill findById();

    /**
     * 暴露秒杀接口URL
     *
     * <p>秒杀开启时输出秒杀接口地址, 否则输出系统时间和秒杀时间</p>
     *
     * @param seckillId 秒杀商品id
     * @return 秒杀地址
     */
    SeckillURL exposeSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     *
     * @param seckillId 商品id
     * @param userPhone 用户手机号
     * @param md5 加密后的秒杀地址, 用于验证秒杀请求是否合法
     */
    void executeSeckill(long seckillId, long userPhone, String md5);
}

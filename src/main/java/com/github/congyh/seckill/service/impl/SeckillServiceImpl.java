package com.github.congyh.seckill.service.impl;

import com.github.congyh.seckill.cache.RedisCache;
import com.github.congyh.seckill.dao.ProductMapper;
import com.github.congyh.seckill.dao.OrderDetailMapper;
import com.github.congyh.seckill.entity.Product;
import com.github.congyh.seckill.enums.SeckillExecutionStatus;
import com.github.congyh.seckill.exception.RepeatKillException;
import com.github.congyh.seckill.exception.SeckillEndException;
import com.github.congyh.seckill.exception.SeckillException;
import com.github.congyh.seckill.exception.WrongURLException;
import com.github.congyh.seckill.model.SeckillExecutionResult;
import com.github.congyh.seckill.model.SeckillURL;
import com.github.congyh.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private static Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);
    // 提高MD5算法的加密程度
    // TODO 前端部分, 也就是product-detail.ftl中md5与productId会一并传递过去, 还是不安全??
    private static final String SALT = "gatg25tagfgp['lf[pal[;l,.l;";

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<Product> findAll() {
        return productMapper.findAll(0, 4);
    }

    @Override
    public Product findById(long productId) {
        return productMapper.findById(productId);
    }

    @Override
    public SeckillURL exposeSeckillUrl(long productId) {
        // TODO 缓存最后要以横切的方式实现
        Product product = redisCache.getProduct(productId);
        if (product == null) {
            product = productMapper.findById(productId);
            // TODO 空指针异常最好也统一处理, 比较困难的是如何自动判别空的类型? 还是直接不判断了?
        }

        Date startTime = product.getStartTime();
        Date endTime = product.getEndTime();
        // 首先需要判断秒杀是否开启, 如果没有开启输出服务器时间和秒杀时间范围
        Date now = new Date();
        if (now.getTime() < startTime.getTime()
            || now.getTime() > endTime.getTime()) {
            return new SeckillURL(false, now.getTime(), startTime.getTime(), endTime.getTime());
        }
        String seckillURL = toMD5(productId);
        return new SeckillURL(true, seckillURL, productId);
    }

    /**
     * 根据传入的秒杀商品生成md5加密后的秒杀地址
     *
     * @param productId 秒杀商品id
     * @return md5加密后的秒杀地址
     */
    private String toMD5(long productId) {
        String base = productId + "/" + SALT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    /**
     * 执行秒杀
     *
     * <pre>注意: 事务中语句的顺序能够极大影响程序并发能力, 例如下面的程序
     * 1. 将秒杀地址是否正确的判断放在最前, 这是一个本地的判断, 速度很快.
     * 2. 尝试进行秒杀成功记录操作. 注意: 将插入操作放在前面是为了在rowLock发生之前可以
     * 淘汰掉一部分重复秒杀情况, 同时将事务操作的瓶颈减少到实际发生rowLock的语句上,
     * 因为其他语句都可以无锁并发执行.
     * 3. 真正的rowLock发生地, 减库存操作.
     * </pre>
     *
     * @param productId 商品id
     * @param userPhone 用户手机号
     * @param md5 加密后的秒杀地址, 用于验证秒杀请求是否合法
     * @return 秒杀结果
     * @throws SeckillException 秒杀异常
     */
    @Override
    @Transactional
    public SeckillExecutionResult executeSeckill(long productId, long userPhone, String md5)
        throws SeckillException {

        if (md5 == null || !md5.equals(toMD5(productId))) {
            throw new WrongURLException();
        }
        if (orderDetailMapper.save(productId, userPhone) == 0) {
            throw new RepeatKillException();
        }
        if (productMapper.reduceNumber(productId, new Date()) == 0) {
            throw new SeckillEndException();
        }

        return new SeckillExecutionResult(productId, userPhone, SeckillExecutionStatus.SUCCESS);
    }
}

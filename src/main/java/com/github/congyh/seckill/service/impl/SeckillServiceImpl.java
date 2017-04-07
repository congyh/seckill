package com.github.congyh.seckill.service.impl;

import com.github.congyh.seckill.dao.SeckillProductDAO;
import com.github.congyh.seckill.dao.RedisDAO;
import com.github.congyh.seckill.dao.SeckillOrderDAO;
import com.github.congyh.seckill.domain.SeckillProductDO;
import com.github.congyh.seckill.dto.SeckillExecutionDTO;
import com.github.congyh.seckill.dto.SeckillUrlDTO;
import com.github.congyh.seckill.enums.SeckillExecutionStatusEnum;
import com.github.congyh.seckill.exception.ServiceException;
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
    private SeckillProductDAO seckillProductDAO;

    @Autowired
    private SeckillOrderDAO seckillOrderDAO;

    @Autowired
    private RedisDAO redisDAO;

    @Override
    public List<SeckillProductDO> findAll() {
        return seckillProductDAO.findAll(0, 4);
    }

    @Override
    public SeckillProductDO findById(long productId) {
        return seckillProductDAO.findById(productId);
    }

    @Override
    public SeckillUrlDTO exposeSeckillUrl(long productId) {
        // TODO 缓存最后要以横切的方式实现
        SeckillProductDO seckillProductDO = redisDAO.getProduct(productId);
        if (seckillProductDO == null) {
            seckillProductDO = seckillProductDAO.findById(productId);
            // TODO 空指针异常最好也统一处理, 比较困难的是如何自动判别空的类型? 还是直接不判断了?
        }

        Date startTime = seckillProductDO.getStartTime();
        Date endTime = seckillProductDO.getEndTime();
        // 首先需要判断秒杀是否开启, 如果没有开启输出服务器时间和秒杀时间范围
        Date now = new Date();
        if (now.getTime() < startTime.getTime()
            || now.getTime() > endTime.getTime()) {
            return new SeckillUrlDTO(false, now.getTime(), startTime.getTime(), endTime.getTime());
        }
        String seckillURL = toMD5(productId);
        return new SeckillUrlDTO(true, seckillURL, productId);
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
     * @throws ServiceException 秒杀异常
     */
    @Override
    @Transactional
    public SeckillExecutionDTO executeSeckill(long productId, long userPhone, String md5)
        throws ServiceException {

        if (md5 == null || !md5.equals(toMD5(productId))) {
            throw new ServiceException("秒杀地址错误!");
        }
        if (seckillOrderDAO.save(null, productId, userPhone) == 0) {
            throw new ServiceException("您已成功秒杀, 无法重复秒杀!");
        }
        if (seckillProductDAO.reduceNumber(productId, new Date()) == 0) {
            throw new ServiceException("该商品的秒杀已经结束!");
        }

        return new SeckillExecutionDTO(productId, userPhone, SeckillExecutionStatusEnum.SUCCESS);
    }
}

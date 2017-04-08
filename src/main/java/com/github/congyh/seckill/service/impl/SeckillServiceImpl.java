package com.github.congyh.seckill.service.impl;

import com.github.congyh.seckill.constant.CacheConsts;
import com.github.congyh.seckill.dao.RedisDAO;
import com.github.congyh.seckill.dao.SeckillOrderDAO;
import com.github.congyh.seckill.dao.SeckillProductDAO;
import com.github.congyh.seckill.domain.SeckillProductDO;
import com.github.congyh.seckill.dto.Result;
import com.github.congyh.seckill.dto.SeckillExecutionDTO;
import com.github.congyh.seckill.dto.SeckillUrlDTO;
import com.github.congyh.seckill.enums.ResultTypeEnum;
import com.github.congyh.seckill.exception.DAOException;
import com.github.congyh.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    private static final Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    private static final String SECKILL_URL_KEY = "seckillUrl";
    // 提高MD5算法的加密程度
    // TODO 前端部分, 也就是product-detail.ftl中md5与productId会一并传递过去, 还是不安全??
    private static final String SALT = "gatg25tagfgp['lf[pal[;l,.l;";

    @Autowired
    private SeckillProductDAO seckillProductDAO;

    @Autowired
    private SeckillOrderDAO seckillOrderDAO;

    @Override
    @Cacheable(cacheNames = CacheConsts.PRODUCT_LIST_KEY)
    public List<SeckillProductDO> findAll() throws DAOException {
        try {
            return seckillProductDAO.findAll(0, 4);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public SeckillProductDO findById(long productId) throws DAOException {
        try {
            return seckillProductDAO.findById(productId);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    @Cacheable(cacheNames = SECKILL_URL_KEY)
    public SeckillUrlDTO exposeSeckillUrl(long seckillProductId) throws DAOException {
        try {
            SeckillProductDO seckillProduct = seckillProductDAO.findById(seckillProductId);
            Date gmtStart = seckillProduct.getGmtStart();
            Date gmtEnd = seckillProduct.getGmtEnd();
            // 首先需要判断秒杀是否开启, 如果没有开启输出服务器时间和秒杀时间范围
            Date gmtNow = new Date();
            if (gmtNow.getTime() < gmtStart.getTime()
                || gmtNow.getTime() > gmtEnd.getTime()) {
                return null;
            }
            String md5 = toMD5(seckillProductId);
            return new SeckillUrlDTO(seckillProductId, md5);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    /**
     * 根据传入的秒杀商品生成md5加密后的秒杀地址
     *
     * @param seckillProductId 秒杀商品id
     * @return md5加密后的秒杀地址
     */
    private String toMD5(long seckillProductId) {
        String base = seckillProductId + "/" + SALT;
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
     * @param seckillProductId 商品id
     * @param userPhone 用户手机号
     * @param md5 加密后的秒杀地址, 用于验证秒杀请求是否合法
     * @return 秒杀结果
     */
    @Override
    @Transactional
    public Result<SeckillExecutionDTO> executeSeckill(long seckillProductId, long userPhone, String md5)
        throws DAOException {

        if (md5 == null || !md5.equals(toMD5(seckillProductId))) {
            return new Result<>(ResultTypeEnum.WRONG_URL);
        }
        try {
            if (seckillOrderDAO.save(seckillProductId, userPhone) == 0) {
                return new Result<>(ResultTypeEnum.SECKILL_REPEAT);
            }
            if (seckillProductDAO.reduceNumber(seckillProductId, new Date()) == 0) {
                return new Result<>(ResultTypeEnum.SECKILL_END);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }

        SeckillExecutionDTO seckillExecution = new SeckillExecutionDTO(seckillProductId, userPhone);
        return new Result<>(ResultTypeEnum.SECKILL_SUCCESS, seckillExecution);
    }
}

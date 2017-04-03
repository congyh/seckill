package com.github.congyh.seckill.service.impl;

import com.github.congyh.seckill.dao.SeckillMapper;
import com.github.congyh.seckill.dao.SuccessKilledMapper;
import com.github.congyh.seckill.entity.Seckill;
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
    // TODO 还没有搞明白具体的作用是什么
    // 提高MD5算法的加密程度
    private static final String SALT = "gatg25tagfgp['lf[pal[;l,.l;";

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private SuccessKilledMapper successKilledMapper;

    @Override
    public List<Seckill> findAll() {
        return seckillMapper.findAll(0, 4);
    }

    @Override
    public Seckill findById(long seckillId) {
        return seckillMapper.findById(seckillId);
    }

    @Override
    public SeckillURL exposeSeckillUrl(long seckillId) {
        Seckill seckill = seckillMapper.findById(seckillId);
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        // 首先需要判断秒杀是否开启, 如果没有开启输出服务器时间和秒杀时间范围
        Date now = new Date();
        if (now.getTime() < startTime.getTime()
            || now.getTime() > endTime.getTime()) {
            return new SeckillURL(false, now.getTime(), startTime.getTime(), endTime.getTime());
        }
        String seckillURL = toMD5(seckillId);
        return new SeckillURL(true, seckillURL, seckillId);
    }

    /**
     * 根据传入的秒杀商品生成md5加密后的秒杀地址
     *
     * @param seckillId 秒杀商品id
     * @return md5加密后的秒杀地址
     */
    private String toMD5(long seckillId) {
        String base = seckillId + "/" + SALT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Override
    @Transactional
    public SeckillExecutionResult executeSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException {

        // 如果地址没有传过来, 或者是地址不符合按我们算法加密后的URL值
        if (md5 == null || !md5.equals(toMD5(seckillId))) {
            throw new WrongURLException("秒杀地址错误!");
        }
        // 执行业务逻辑
        // 1. 尝试进行减库存操作
        if (seckillMapper.reduceNumber(seckillId, new Date()) == 0) {
            throw new SeckillEndException("该商品的秒杀已经结束!");
        }
        // 2. 尝试进行秒杀成功记录操作
        if (successKilledMapper.save(seckillId, userPhone) == 0) {
            throw new RepeatKillException("您已成功秒杀, 无法重复秒杀!");
        }

        return new SeckillExecutionResult(seckillId, userPhone, SeckillExecutionStatus.SUCCESS);
    }
}

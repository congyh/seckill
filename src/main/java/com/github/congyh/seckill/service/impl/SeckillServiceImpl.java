package com.github.congyh.seckill.service.impl;

import com.github.congyh.seckill.dao.SeckillMapper;
import com.github.congyh.seckill.entity.Seckill;
import com.github.congyh.seckill.exception.RepeatKillException;
import com.github.congyh.seckill.exception.SeckillEndException;
import com.github.congyh.seckill.model.SeckillExecutionResult;
import com.github.congyh.seckill.model.SeckillURL;
import com.github.congyh.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private static Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired
    private SeckillMapper seckillMapper;

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
        return null;
    }

    @Override
    public SeckillExecutionResult executeSeckill(long seckillId, long userPhone, String md5)
        throws RepeatKillException, SeckillEndException {
        return null;
    }
}

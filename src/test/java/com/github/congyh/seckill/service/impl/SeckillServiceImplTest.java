package com.github.congyh.seckill.service.impl;

import com.github.congyh.seckill.entity.Seckill;
import com.github.congyh.seckill.model.SeckillExecutionResult;
import com.github.congyh.seckill.model.SeckillURL;
import com.github.congyh.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 注意:
 *
 * <pre>
 *     1. 单元测试的代码必须可重复执行.
 *     2. 单元测试的代码覆盖率要有保障.
 * </pre>
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceImplTest {

    private static final Logger logger = getLogger(SeckillServiceImplTest.class);

    @Autowired
    private SeckillService seckillService;

    @Test
    public void findAll() throws Exception {
        List<Seckill> seckillList = seckillService.findAll();
        logger.info("list={}", seckillList);
    }

    @Test
    public void findById() throws Exception {
        logger.info("seckill={}", seckillService.findById(1001));
    }

    @Test
    public void executeSeckill() throws Exception {
        SeckillURL seckillURL = seckillService.exposeSeckillUrl(1003);
        if (seckillURL.isExposed()) {
            logger.info("SeckillURL={}", seckillURL);

            // URL地址为类似: 2315285e41247936a4184a3085704469 这样的字符串
            // try catch块是为了保证测试能够重复执行.
            try {
                SeckillExecutionResult seckillExecutionResult = seckillService
                    .executeSeckill(1001, 1800000000L, seckillURL.getMd5());
                logger.info("result={}", seckillExecutionResult);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

        } else {
            logger.warn("秒杀未开启!");
        }

    }

}
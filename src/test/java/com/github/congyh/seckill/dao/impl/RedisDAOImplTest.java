package com.github.congyh.seckill.dao.impl;

import com.github.congyh.seckill.dao.RedisDAO;
import com.github.congyh.seckill.dao.SeckillProductDAO;
import com.github.congyh.seckill.domain.SeckillProductDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDAOImplTest {
    @Autowired
    private RedisDAO redisDAO;

    @Autowired
    private SeckillProductDAO seckillProductDAO;

    @Test
    public void setAndGetSeckill() throws Exception {
        final long id = 1001;
        SeckillProductDO seckillProductDO = redisDAO.getProduct(id);
        if (seckillProductDO == null) {
            seckillProductDO = seckillProductDAO.findById(id);
            if (seckillProductDO != null) {
                redisDAO.setProduct(seckillProductDO);
                System.out.println(seckillProductDO);
                seckillProductDO = redisDAO.getProduct(id);
                System.out.println(seckillProductDO);
            }
        }
    }
}
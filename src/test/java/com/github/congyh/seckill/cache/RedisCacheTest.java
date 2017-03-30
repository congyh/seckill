package com.github.congyh.seckill.cache;

import com.github.congyh.seckill.dao.SeckillMapper;
import com.github.congyh.seckill.entity.Seckill;
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
public class RedisCacheTest {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SeckillMapper seckillMapper;

    @Test
    public void getSeckill() throws Exception {
        // TODO 没写
        System.out.println(seckillMapper.findById(1001));
    }

    @Test
    public void setSeckill() throws Exception {
        // TODO 没写
        Seckill seckill = new Seckill();
        String s = redisCache.setSeckill(seckill);
    }
}
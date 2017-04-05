package com.github.congyh.seckill.cache;

import com.github.congyh.seckill.dao.ProductMapper;
import com.github.congyh.seckill.entity.Product;
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
    private ProductMapper productMapper;

    @Test
    public void getSeckill() throws Exception {
        // TODO 没写
        System.out.println(productMapper.findById(1001));
    }

    @Test
    public void setSeckill() throws Exception {
        // TODO 没写
        Product product = new Product();
        String s = redisCache.setProduct(product);
    }
}
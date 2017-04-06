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
    public void setAndGetSeckill() throws Exception {
        final long id = 1001;
        Product product = redisCache.getProduct(id);
        if (product == null) {
            product = productMapper.findById(id);
            if (product != null) {
                redisCache.setProduct(product);
                System.out.println(product);
                product = redisCache.getProduct(id);
                System.out.println(product);
            }
        }
    }
}
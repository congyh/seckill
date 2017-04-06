package com.github.congyh.seckill.cache;

import com.github.congyh.seckill.dao.ProductDAO;
import com.github.congyh.seckill.dao.RedisDAO;
import com.github.congyh.seckill.domain.ProductDO;
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
public class RedisDAOTest {
    @Autowired
    private RedisDAO redisDAO;

    @Autowired
    private ProductDAO productDAO;

    @Test
    public void setAndGetSeckill() throws Exception {
        final long id = 1001;
        ProductDO productDO = redisDAO.getProduct(id);
        if (productDO == null) {
            productDO = productDAO.findById(id);
            if (productDO != null) {
                redisDAO.setProduct(productDO);
                System.out.println(productDO);
                productDO = redisDAO.getProduct(id);
                System.out.println(productDO);
            }
        }
    }
}
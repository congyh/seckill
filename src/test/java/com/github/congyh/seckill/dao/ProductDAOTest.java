package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.domain.ProductDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDAOTest {

    @Autowired
    ProductDAO productDAO;

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        int affectedRowNums = productDAO.reduceNumber(1000, killTime);
        assertTrue(1 == affectedRowNums);
    }

    @Test
    public void findById() throws Exception {
        ProductDO productDO = productDAO.findById(1001);
        System.out.println(productDO);
    }

    @Test
    public void findAll() throws Exception {
        List<ProductDO> list = productDAO.findAll(0, 5);
        for (ProductDO productDO : list) {
            System.out.println(productDO);
        }
    }

}
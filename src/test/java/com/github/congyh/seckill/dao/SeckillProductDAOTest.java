package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.domain.SeckillProductDO;
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
public class SeckillProductDAOTest {

    @Autowired
    SeckillProductDAO seckillProductDAO;

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        int affectedRowNums = seckillProductDAO.reduceNumber(1000, killTime);
        assertTrue(1 == affectedRowNums);
    }

    @Test
    public void findById() throws Exception {
        SeckillProductDO seckillProductDO = seckillProductDAO.findById(1001);
        System.out.println(seckillProductDO);
    }

    @Test
    public void findAll() throws Exception {
        List<SeckillProductDO> list = seckillProductDAO.findAll(0, 5);
        for (SeckillProductDO seckillProductDO : list) {
            System.out.println(seckillProductDO);
        }
    }

}
package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.entity.Seckill;
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
public class SeckillMapperTest {

    @Autowired
    SeckillMapper seckillMapper;

    @Test
    public void reduceNumber() throws Exception {
        Date killTime = new Date();
        int affectedRowNums = seckillMapper.reduceNumber(1000, killTime);
        assertTrue(1 == affectedRowNums);
    }

    @Test
    public void findById() throws Exception {
        Seckill seckill = seckillMapper.findById(1001);
        System.out.println(seckill);
    }

    @Test
    public void findAll() throws Exception {
        List<Seckill> list = seckillMapper.findAll(0, 5);
        for (Seckill seckill : list) {
            System.out.println(seckill);
        }
    }

}
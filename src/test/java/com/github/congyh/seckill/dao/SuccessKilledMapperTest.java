package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SuccessKilledMapperTest {

    @Autowired
    private SuccessKilledMapper successKilledMapper;

    @Test
    public void save() throws Exception {
        int saveStatus = successKilledMapper.save(1000, 18810374850L);
        System.out.println("影响了几行: " + saveStatus + " 行");
    }

    @Test
    public void findByIdAndPhone() throws Exception {
        SuccessKilled successKilled = successKilledMapper
            .findByIdAndPhone(1000, 18810374850L);
        System.out.println(successKilled);
    }

}
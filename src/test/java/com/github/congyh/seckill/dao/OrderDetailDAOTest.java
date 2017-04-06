package com.github.congyh.seckill.dao;

import com.github.congyh.seckill.domain.OrderDetailDO;
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
public class OrderDetailDAOTest {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Test
    public void save() throws Exception {
        int saveStatus = orderDetailDAO.save(null, 1000, 18823423441L);
        System.out.println("影响了几行: " + saveStatus + " 行");
    }

    @Test
    public void findByIdAndPhone() throws Exception {
        OrderDetailDO orderDetailDO = orderDetailDAO
            .findByIdAndPhone(1000, 18823423441L);
        System.out.println(orderDetailDO);
    }

}
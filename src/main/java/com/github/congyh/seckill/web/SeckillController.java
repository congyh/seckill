package com.github.congyh.seckill.web;

import com.github.congyh.seckill.domain.ProductDO;
import com.github.congyh.seckill.dto.Result;
import com.github.congyh.seckill.dto.SeckillExecutionDTO;
import com.github.congyh.seckill.dto.SeckillUrlDTO;
import com.github.congyh.seckill.service.SeckillService;
import com.github.congyh.seckill.util.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private static final Logger logger = LoggerFactory.getLogger(SeckillController.class);

    @Autowired
    private SeckillService seckillService;

    /**
     * 获取商品列表
     *
     * @return 商品列表页面
     */
    @GetMapping("/products")
    public String products(Model model) {
       List<ProductDO> productDOList = seckillService.findAll();
       model.addAttribute("productDOList", productDOList);

       return "products";
    }

    /**
     * 商品详情页
     *
     * @param id 商品id
     * @param model 商品信息
     * @return 商品详情页地址
     */
    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable("id") long id,
                           Model model) {
        // TODO 这里的product可能是null, 需要进行统一异常处理.
        ProductDO productDO = seckillService.findById(id);
        model.addAttribute("productDO", productDO);

        return "productDO-detail";
    }


    /**
     * 暴露秒杀URL地址给ajax调用
     *
     * @param id 秒杀商品id
     * @return 秒杀地址
     */
    @GetMapping("/products/{id}/seckillUrl")
    @ResponseBody
    public Result<SeckillUrlDTO> seckillUrl(@PathVariable("id") long id) {
        SeckillUrlDTO seckillUrlDTO = seckillService.exposeSeckillUrl(id);
        return ResultUtils.success(seckillUrlDTO);
    }

    /**
     * 执行秒杀操作
     *
     * @param id 秒杀商品id
     * @param url 秒杀url
     * @param userPhone 用户手机号
     * @return 秒杀执行结果
     */
    @GetMapping("/products/{id}/{url}")
    @ResponseBody
    public Result<SeckillExecutionDTO> seckillUrl(@PathVariable("id") long id,
                                                  @PathVariable("url") String url,
                                                  @CookieValue(value = "userPhone", required = false) long userPhone) {
        SeckillExecutionDTO seckillExecutionDTO
             = seckillService.executeSeckill(id, userPhone, url);
        return ResultUtils.success(seckillExecutionDTO);
    }


}

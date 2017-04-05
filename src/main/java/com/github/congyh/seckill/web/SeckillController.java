package com.github.congyh.seckill.web;

import com.github.congyh.seckill.entity.Product;
import com.github.congyh.seckill.model.JsonResponse;
import com.github.congyh.seckill.model.SeckillURL;
import com.github.congyh.seckill.service.SeckillService;
import com.github.congyh.seckill.utils.JsonResponseUtils;
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
       List<Product> productList = seckillService.findAll();
       model.addAttribute("productList", productList);

       return "products";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable("id") long id,
                           Model model) {
        // TODO 这里的product可能是null, 需要进行统一异常处理.
        Product product = seckillService.findById(id);
        model.addAttribute("product", product);

        return "productDetail";
    }

    /**
     * 暴露秒杀URL地址给ajax调用
     *
     * @param id 秒杀商品id
     * @return
     */
    @PostMapping("/products/{id}/url")
    @ResponseBody
    public JsonResponse<SeckillURL> seckillUrl(@PathVariable("id") long id) {
        SeckillURL seckillURL = seckillService.exposeSeckillUrl(id);
        return JsonResponseUtils.success(seckillURL);
    }

}
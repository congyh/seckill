package com.github.congyh.seckill.dto;

/**
 * 秒杀地址封装类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillUrlDTO {

    private Long productId;
    /** 一种加密措施 */
    private String md5;

    public SeckillUrlDTO(Long productId, String md5) {
        this.md5 = md5;
        this.productId = productId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

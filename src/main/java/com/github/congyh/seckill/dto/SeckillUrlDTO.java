package com.github.congyh.seckill.dto;

/**
 * 秒杀地址封装类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillUrlDTO {

    private Boolean exposed;
    /** 一种加密措施 */
    private String md5;
    private Long productId;
    /** 服务器当前时间 */
    private Long now;
    private Long start;
    private Long end;

    public SeckillUrlDTO(Boolean exposed, String md5, Long productId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.productId = productId;
    }

    public SeckillUrlDTO(Boolean exposed, Long productId) {
        this.exposed = exposed;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "SeckillUrlDTO{" +
            "exposed=" + exposed +
            ", md5='" + md5 + '\'' +
            ", productId=" + productId +
            ", now=" + now +
            ", start=" + start +
            ", end=" + end +
            '}';
    }

    public Boolean getExposed() {
        return exposed;
    }

    public void setExposed(Boolean exposed) {
        this.exposed = exposed;
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

    public Long getNow() {
        return now;
    }

    public void setNow(Long now) {
        this.now = now;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}

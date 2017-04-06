package com.github.congyh.seckill.dto;

/**
 * 秒杀地址封装类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillUrlDTO {

    private boolean exposed;
    /** 一种加密措施 */
    private String md5;
    private long productId;
    /** 服务器当前时间 */
    private long now;
    private long start;
    private long end;

    public SeckillUrlDTO(boolean exposed, String md5, long productId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.productId = productId;
    }

    public SeckillUrlDTO(boolean exposed, long productId) {
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

    public SeckillUrlDTO(boolean exposed, long now, long start, long end) {

        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return productId;
    }

    public void setSeckillId(long productId) {
        this.productId = productId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}

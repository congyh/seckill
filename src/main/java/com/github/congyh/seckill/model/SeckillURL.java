package com.github.congyh.seckill.model;

/**
 * 秒杀地址封装类
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillURL {

    private boolean exposed;
    /** 一种加密措施 */
    private String md5;
    private Long seckillId;
    /** 服务器当前时间 */
    private Long now;
    private Long start;
    private Long end;

    public SeckillURL(Boolean exposed, String md5, Long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public SeckillURL(Boolean exposed, Long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public SeckillURL(Boolean exposed, Long now, Long start, Long end) {

        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
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

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
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

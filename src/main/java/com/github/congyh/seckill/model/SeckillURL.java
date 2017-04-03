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
    private long seckillId;
    /** 服务器当前时间 */
    private long now;
    private long start;
    private long end;

    public SeckillURL(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public SeckillURL(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    @Override
    public String toString() {
        return "SeckillURL{" +
            "exposed=" + exposed +
            ", md5='" + md5 + '\'' +
            ", seckillId=" + seckillId +
            ", now=" + now +
            ", start=" + start +
            ", end=" + end +
            '}';
    }

    public SeckillURL(boolean exposed, long now, long start, long end) {

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
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
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

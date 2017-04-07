package com.github.congyh.seckill.domain;

import java.util.Date;

/**
 * 秒杀商品实体
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class SeckillProductDO {
    private Long id;
    private String name;
    private Integer number;
    private Date gmtStart;
    private Date gmtEnd;

    @Override
    public String toString() {
        return "SeckillProductDO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", number=" + number +
            ", gmtStart=" + gmtStart +
            ", gmtEnd=" + gmtEnd +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }
}

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
    private Date startTime;
    private Date endTime;
    private Date createTime;

    @Override
    public String toString() {
        return "SeckillProductDO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", number=" + number +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", createTime=" + createTime +
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

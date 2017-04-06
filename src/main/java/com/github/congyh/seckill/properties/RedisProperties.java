package com.github.congyh.seckill.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@ConfigurationProperties("redis")
public class RedisProperties {
    private String ip;
    private Integer port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "RedisProperties{" +
            "ip='" + ip + '\'' +
            ", port=" + port +
            '}';
    }
}

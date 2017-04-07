package com.github.congyh.seckill.config;

import com.github.congyh.seckill.dao.RedisDAO;
import com.github.congyh.seckill.dao.impl.RedisDAOImpl;
import com.github.congyh.seckill.properties.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Redis缓存属性
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Bean
    public RedisDAO initRedisCache(RedisProperties redisProperties) {
        return new RedisDAOImpl(redisProperties.getIp(), redisProperties.getPort());
    }
}

package com.github.congyh.seckill.config;

import com.github.congyh.seckill.cache.RedisCache;
import com.github.congyh.seckill.properties.RedisCacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Redis缓存属性
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Configuration
@EnableConfigurationProperties(RedisCacheProperties.class)
public class RedisCacheConfig {

    @Bean
    public RedisCache initRedisCache(RedisCacheProperties redisCacheProperties) {
        return new RedisCache(redisCacheProperties.getIp(), redisCacheProperties.getPort());
    }
}

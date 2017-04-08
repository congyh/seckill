package com.github.congyh.seckill.config;

import com.github.congyh.seckill.constant.CacheConsts;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManagerCustomizer<RedisCacheManager> cacheManagerCustomizer() {
        return new CacheManagerCustomizer<RedisCacheManager>() {
            @Override
            public void customize(RedisCacheManager cacheManager) {
                // 设定缓存key默认前缀
                cacheManager.setCachePrefix(CacheConsts.DEFAULT_KEY_PREFIX);
                cacheManager.setUsePrefix(true);
                cacheManager.setDefaultExpiration(CacheConsts.DEFAULT_EXPIRE_TIME);
                // 单独设定商品列表的失效时间
                cacheManager.setExpires(toParam(
                    CacheConsts.PRODUCT_LIST_KEY, CacheConsts.PRODUCT_LIST_EXPIRE_TIME));
            }
        };
    }

    private static Map<String, Long> toParam(final String cacheKey, final long expire) {
        Map<String, Long> params = new HashMap<>();
        params.put(cacheKey, expire);
        return params;
    }
}

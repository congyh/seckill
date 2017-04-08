package com.github.congyh.seckill.constant;

import org.springframework.data.redis.cache.RedisCachePrefix;

/**
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public final class CacheConsts {
    private CacheConsts() {}

    public static final String DEFAULT_KEY_PATTERN = "seckill:*";

    public static final RedisCachePrefix DEFAULT_KEY_PREFIX = new RedisCachePrefix() {
        @Override
        public byte[] prefix(String cacheName) {
            return "seckill:".getBytes();
        }
    };

    public static final long DEFAULT_EXPIRE_TIME = 3600L;

    public static final long PRODUCT_LIST_EXPIRE_TIME = 10L;

    public static final String PRODUCT_LIST_KEY = "productList";
}

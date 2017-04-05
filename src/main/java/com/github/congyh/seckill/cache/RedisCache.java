package com.github.congyh.seckill.cache;

import com.github.congyh.seckill.entity.Product;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * Redis缓存交互
 *
 * <pre>
 * 使用第三方序列化lib--protostuff进行序列化与反序列化.
 * </pre>
 *
 * @author <a href="mailto:yihao.cong@outlook.com">Cong Yihao</a>
 */
public class RedisCache {

    private static Logger logger = LoggerFactory.getLogger(RedisCache.class);
    // 动态生成序列化所用的schema
    private static RuntimeSchema<Product> schema = RuntimeSchema.createFrom(Product.class);

    private final JedisPool jedisPool;

    public RedisCache(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    /**
     * 从Redis中读取对象
     *
     * @param productId 对象id
     * @return 对象
     */
    public Product getProduct(long productId) {
        try (Jedis jedis = jedisPool.getResource()) {
            /* 反序列化过程: get(byte[]) -> Product */
            String key = "seckill:" + productId;
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                // 生成空对象
                Product product = schema.newMessage();
                // 将字节数组中的内容, 按照schema, 传递到空对象中.
                ProtobufIOUtil.mergeFrom(bytes, product, schema);
                return product;
            }
        }
        return null;
    }

    /**
     * 向Redis中写入对象
     *
     * @param product 待写入的对象
     * @return 写入状态, 如果正确是OK, 如果错误是错误信息字符串
     */
    public String setProduct(Product product) {
        try (Jedis jedis = jedisPool.getResource()) {
            /* 序列化过程: Product -> byte[] */
            String key = "product:" + product.getId();
            byte[] bytes = ProtobufIOUtil.toByteArray(product, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

            // 设置缓存失效时间, 单位: 秒
            int timeout = 60 * 60;
            // 正确的话返回OK, 错误的话返回错误信息
            String result = jedis.setex(key.getBytes(), timeout, bytes);
            return result;
        }
    }
}

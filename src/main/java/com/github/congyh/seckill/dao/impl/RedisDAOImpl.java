package com.github.congyh.seckill.dao.impl;

import com.github.congyh.seckill.dao.RedisDAO;
import com.github.congyh.seckill.domain.SeckillProductDO;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Deprecated
public class RedisDAOImpl implements RedisDAO {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 动态生成序列化所用的schema
    private static RuntimeSchema<SeckillProductDO> schema = RuntimeSchema.createFrom(SeckillProductDO.class);

    @Autowired
    private final JedisPool jedisPool;

    public RedisDAOImpl(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    @Override
    public SeckillProductDO getProduct(long productId) {
        try (Jedis jedis = jedisPool.getResource()) {
            /* 反序列化过程: get(byte[]) -> SeckillProductDO */
            String key = "seckillProduct:" + productId;
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                // 生成空对象
                SeckillProductDO seckillProduct = schema.newMessage();
                // 将字节数组中的内容, 按照schema, 传递到空对象中.
                ProtobufIOUtil.mergeFrom(bytes, seckillProduct, schema);
                return seckillProduct;
            }
        }
        return null;
    }

    @Override
    public String setProduct(SeckillProductDO seckillProduct) {
        try (Jedis jedis = jedisPool.getResource()) {
            /* 序列化过程: SeckillProductDO -> byte[] */
            String key = "seckillProduct:" + seckillProduct.getId();
            byte[] bytes = ProtobufIOUtil.toByteArray(seckillProduct, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

            // 设置缓存失效时间, 单位: 秒
            int timeout = 60 * 60;
            // 正确的话返回OK, 错误的话返回错误信息
            String result = jedis.setex(key.getBytes(), timeout, bytes);

            return result;
        }
    }
}

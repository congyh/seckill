# 数据库初始化脚本
drop DATABASE seckill;
CREATE DATABASE seckill;
USE seckill;
CREATE TABLE seckill_product(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` INT UNSIGNED NOT NULL COMMENT '库存数量',
  `gmt_start` DATETIME NOT NULL COMMENT '秒杀开启时间',
  `gmt_end` DATETIME NOT NULL COMMENT '秒杀结束时间',
  `gmt_create` DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id)
)ENGINE = InnoDB AUTO_INCREMENT = 1000 DEFAULT CHARSET = utf8 COMMENT = '秒杀商品库存表';

# 初始化数据
INSERT INTO seckill_product(name, number, gmt_start, gmt_end)
VALUES
  ('1000元秒杀iphone6', 100, '2017-03-29 00:00:00', '2018-03-30 00:00:00'),
  ('500元秒杀ipad2', 200, '2017-03-29 00:00:00', '2018-03-30 00:00:00'),
  ('300元秒杀小米4', 300, '2017-03-29 00:00:00', '2017-03-30 00:00:00'),
  ('200元秒杀红米note', 400, '2018-03-29 00:00:00', '2018-03-30 00:00:00');

# 秒杀成功明细表
CREATE TABLE `seckill_order`(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `seckill_product_id` BIGINT UNSIGNED NOT NULL COMMENT '秒杀商品id',
  `user_phone` BIGINT UNSIGNED NOT NULL COMMENT '用户手机号码',
  `order_status` TINYINT UNSIGNED NOT NULL DEFAULT  0 COMMENT '状态标志 : 0, 默认; 1, 已付款; 2, 已发货; 3, 已收货',
  `gmt_create` DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  `gmt_modified` DATETIME NOT NULL DEFAULT current_timestamp COMMENT '修改时间',
  PRIMARY KEY (id),
  UNIQUE KEY (seckill_product_id, user_phone) # 联合索引
) ENGINE = InnoDB AUTO_INCREMENT = 10000 DEFAULT CHARSET = utf8 COMMENT '秒杀订单明细表';
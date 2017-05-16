# 秒杀系统

基于SpringBoot+Mybatis+Freemarker+MySQL+Redis构建.

## 使用方法

1. 首先导入src/resources/sql/schema.sql到mysql中.
1. 更改`application.yml`中配置mysql和Redis的连接.
1. 开启MySQL和Redis服务
1. 然后使用以下方法启用程序(需要安装maven):

### 方法一

进入工程根目录

```bash
mvn spring-boot:run
```

访问http://localhost:8081/seckill/products

### 方法二

使用maven打包, 然后执行:

```bash
java -jar <包名>.jar
```

访问http://localhost:8081/seckill/products

### 方法三

1. 使用Eclipse/Intellij IDEA/NetBeans导入工程.
1. 直接右键run主类`SpringBootSeckkillApplication`
1. 访问http://localhost:8081/seckill/products

操作步骤示例: 

![操作步骤](http://image.congyh.com/17-5-16/59966117-file_1494948633629_183cd.gif)

示例结果:

![程序主页](http://image.congyh.com/17-5-16/44698377-file_1494947897778_13769.png)

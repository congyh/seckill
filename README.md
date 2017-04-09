# 秒杀系统

基于SpringBoot+Mybatis+Freemarker+MySQL+Redis构建.

## 使用方法

1. 首先导入src/resources/sql/schema.sql到mysql中.
1. 更改`application.yml`中配置mysql和Redis的连接.
1. 然后使用以下方法启用:

### 方法一

```bash
mvn spring-boot:run
```

### 方法二

```bash
java -jar <包名>.jar
```

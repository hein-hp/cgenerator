# 小而美的代码生成器

## 介绍

一个小而美的代码生成器，操作简单，不依赖任何插件，自动生成 entity、mapper、service 等大量重复代码。

## 使用说明

整体只有三步，生成之后即可删除 maven 依赖和相关配置文件。

### 导入 maven 依赖

```xml
<dependency>
    <groupId>org.hein</groupId>
    <artifactId>cgenerator</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 编写配置文件

名称 cgenerator-config.yaml

示例如下：

```yaml
cgenerator:
  # 数据源配置
  database:
    jdbc:
      url: jdbc:mysql://ip:port/database?useSSL=false&serverTimezone=UTC
      username: username
      password: password
      driver: com.mysql.cj.jdbc.Driver
  # 表生成参数配置
  generator:
    # 生成表
    generate-table:
      all: false
      names:
        - table-name
    # 忽略表前缀
    ignore-table-prefix:
      ignore: true
      values:
        - t_
    # 生成包结构
    packages:
      entity: org.hein.dao.entity
      mapper: org.hein.dao.mapper
      service: org.hein.service
      controller: org.hein.controller
    # 生成类型：可选 mybatis | mybatis-plus
    generate-type: mybatis
  extra:
    author: hein
```

### 运行 GenerateBootstrap 类

在任意一个类中，编写 main 方法：

```java
public static void main(String[] args) {
    GenerateBootstrap.doGenerate();
}
```
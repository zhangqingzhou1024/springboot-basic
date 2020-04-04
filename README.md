
技术架构：SpringBoot + Mybatis + Redis + Dubbo + Zookeeper

-common:
    常用基础包，包括常用request、response和一些常用utils(本地缓存、加解密、文件、json等)

-dao：
    集成Mybatis 构建Mapper
    
-domain:
    封装一些DTO、Query和常用常量对象
    
-main：
    系统配置和启动类
    
-restful：
    rest请求入口，controller
    
-service：
    具体的业务执行类

-rpc-api:
    对外暴露（Dubbo）接口定义
    
-rpc-service:
    对外暴露接口具体实现
    
-util:
    常用工具类封装，如 redis的二次封装
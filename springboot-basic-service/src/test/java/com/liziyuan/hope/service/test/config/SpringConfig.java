package com.liziyuan.hope.service.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 引入测试启动相关配置信息
 */
@Configuration
@ImportResource(locations = {"classpath:META-INF/spring/spring-db.xml", "classpath:config/springboot-basic-rpc-consumer.xml", "classpath:**.*.yml"})
public class SpringConfig {
}

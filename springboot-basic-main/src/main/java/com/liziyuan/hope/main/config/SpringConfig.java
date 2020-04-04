package com.liziyuan.hope.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 引入 spring 配置
 * <p>
 * spring -> mybatis
 *
 * @author zqz
 * @version V1.0
 * @Description:
 * @date 2019-05-19 12:46
 */
@Configuration
@ImportResource(locations = {"classpath:META-INF/spring/spring-db.xml", "classpath:**.*.yml"})
public class SpringConfig {
}

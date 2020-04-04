package com.liziyuan.hope.rpc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * RpcSpringConfig
 * 引入 rpc-provider 配置信息
 *
 * @author zqz
 * @version 1.0
 **/
@Configuration
@ImportResource(locations = {"classpath:config/springboot-basic-rpc-provider.xml"})
public class RpcSpringConfig {

}

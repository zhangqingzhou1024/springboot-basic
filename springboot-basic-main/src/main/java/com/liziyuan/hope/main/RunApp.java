package com.liziyuan.hope.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * springboot 启动类
 *
 * @author zqz
 * @version V1.0
 * @date 2019-04-30 19:59
 */
@MapperScan(basePackages = "com.liziyuan.hope.dao")
@ComponentScan("com.liziyuan.hope")
@SpringBootApplication
public class RunApp {

    public static void main(String[] args) {

        SpringApplication.run(RunApp.class, args);


    }
}

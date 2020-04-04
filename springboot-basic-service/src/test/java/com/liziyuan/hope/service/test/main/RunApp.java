package com.liziyuan.hope.service.test.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-04-02 22:22
 */
@MapperScan(basePackages = "com.liziyuan.hope.dao")
@ComponentScan("com.liziyuan.hope")
@SpringBootApplication
public class RunApp {
    public static void main(String[] args) {

        SpringApplication.run(RunApp.class, args);


    }
}

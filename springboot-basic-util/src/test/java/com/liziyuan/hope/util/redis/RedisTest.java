package com.liziyuan.hope.util.redis;

import com.liziyuan.hope.util.main.RunApp;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * redis 方法校验
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-02 22:19
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunApp.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class RedisTest {

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void addTest() {
        redisUtil.set("test", "zqz");

        String test = redisUtil.getStrVal("test");

        System.out.println("val->" + test);

    }
}

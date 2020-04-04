package com.liziyuan.hope.service.test.rpc;

import com.liziyuan.hope.rpc.api.ConnectRpcService;
import com.liziyuan.hope.service.test.main.RunApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 连通测试
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-03 16:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RunApp.class)
@WebAppConfiguration
public class ConnectRpcServiceTest {

    @Autowired
    private ConnectRpcService connectRpcService;

    @Test
    public void connectTest() {

        String test = connectRpcService.connectTest("test");

        System.out.println("rpc-result:" + test);
    }
}

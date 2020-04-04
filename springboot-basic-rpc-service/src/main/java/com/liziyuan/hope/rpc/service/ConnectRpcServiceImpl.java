package com.liziyuan.hope.rpc.service;

import com.liziyuan.hope.rpc.api.ConnectRpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ConnectRpcService实现类
 *
 * @author zqz
 * @version 1.0
 * @title ConnectRpcServiceImpl.java
 */
@Slf4j
@Service("connectRpcService")
public class ConnectRpcServiceImpl implements ConnectRpcService {

    @Override
    public String connectTest(String message) {
        log.info("invoke connectRpcService, message is {}", message);
        return "message is " + message;
    }
}

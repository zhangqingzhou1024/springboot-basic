package com.liziyuan.hope.rpc.api;

/**
 * 连通性测试的dubbo接口
 *
 * @author zqz
 * @version 1.0
 * @title ConnectRpcService.java
 */
public interface ConnectRpcService {

    /**
     * 连通性测试
     *
     * @param message 信息
     * @return 返回的信息
     */
    String connectTest(String message);

}

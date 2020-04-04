package com.liziyuan.hope.domain.constant;

/**
 * 字符串常量
 *
 * @author zqz
 * @version 1.0
 * @date 2019-05-28 23:32
 */
public class StringConst {

    /**
     * 空字符串
     */
    public static final String DEFAULT_STRING = "";

    /**
     * redis nx
     */
    public static final String SET_IF_NOT_EXIST = "NX";

    /**
     * redis ex
     */
    public static final String SET_WITH_EXPIRE_TIME = "EX";

    /**
     * 加锁成功
     */
    public static final String LOCK_SUCCESS = "OK";

    /**
     * 默认时间
     */
    public static final String DEFAULT_TIME = "1970-01-01 08:00:01";

    /**
     * Redis分布式队列默认的处理方法
     */
    public static final String REDIS_DEFAULT_TOPIC_HANDLER = "handleMessage";


    /**
     * appKey
     */
    public static final String APP_KEY_STR = "appkey";

    /**
     * 过期时间
     */
    public static final String EXPIRES_STR = "expires";

    /**
     * 签名
     */
    public static final String SIGNATURE_STR = "signature";


    /**
     * 登录类型
     */
    public static final String LOGIN_TYPE = "login_type";

    /**
     * 用户名
     */
    public static final String USERNAME_WORD = "username";

    /**
     * 密码
     */
    public static final String COMMAND_WORD = "password";

}

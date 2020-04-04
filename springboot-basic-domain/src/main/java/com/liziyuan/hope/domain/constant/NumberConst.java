package com.liziyuan.hope.domain.constant;

/**
 * 数字常量
 *
 * @author zqz
 * @version 1.0
 */
public class NumberConst {

    /**
     * 请求成功标识
     */
    public static final Integer SUCCESS_CODE = 0;

    /**
     * 请求错误标识
     */
    public static final Integer FAILURE_CODE = 1;

    /**
     * -1
     */
    public static final Integer MINUS_ONE = -1;

    /**
     * 0
     */
    public static final int ZERO_INT = 0;

    /**
     * 0
     */
    public static final Integer ZERO = 0;

    /**
     * 1
     */
    public static final Integer ONE = 1;

    /**
     * 2
     */
    public static final Integer TWO = 2;

    /**
     * 3
     */
    public static final Integer THREE = 3;

    /**
     * 4
     */
    public static final Integer FOURTH = 4;

    /**
     * 7
     */
    public static final Integer SEVEN = 7;

    /**
     * 10
     */
    public static final Integer TEN = 10;

    /**
     * 100
     */
    public static final Integer HUNDRED = 100;

    /**
     * 100
     */
    public static final Integer ONE_HUNDRED = 100;

    /**
     * 1000
     */
    public static final Integer ONE_THOUSAND = 1000;

    /**
     * 10000
     */
    public static final Integer TEN_THOUSAND = 10000;

    /**
     * 0
     */
    public static final Long ZERO_LONG = 0L;

    /**
     * 1
     */
    public static final Long ONE_LONG = 1L;

    /**
     * -1
     */
    public static final Long MINUS_ONE_LONG = -1L;

    /**
     * 5
     */
    public static final Long FIVE_LONG = 5L;

    /**
     * 10
     */
    public static final Long TEN_LONG = 10L;

    /**
     * 1
     */
    public static final Double DBL_ONE = 1.0;

    /**
     * 默认当前页面
     */
    public static final Integer DEFAULT_CURRENT_PAGE = 1;

    /**
     * 默认页大小
     */
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 字符大小写差距
     */
    public static final Integer UPPER_LOWER_NUMBER = 32;

    /**
     * 毫秒与秒的进制
     */
    public static final Long MILL_SECONDS = 1000L;

    /**
     * 半分钟的秒数
     */
    public static final Long HALF_MINUTE_SECONDS = 30L;

    /**
     * 一分钟的秒数
     */
    public static final Long MINUTE_SECONDS = 60L;

    /**
     * 一小时的秒数
     */
    public static final Long HOUR_SECOND = 3600L;

    /**
     * 一小时的秒数，正统写法
     */
    public static final Long HOUR_SECONDS = 3600L;

    /**
     * 一天的秒数
     */
    public static final Long DAY_SECOND = 86400L;

    /**
     * 一天的秒数，正统写法
     */
    public static final Long DAY_SECONDS = 86400L;

    /**
     * 两天的秒数
     */
    public static final Long TWO_DAY_SECOND = 172800L;

    /**
     * 两天的秒数
     */
    public static final Long TWO_DAY_SECONDS = 172800L;

    /**
     * 单次查询批量
     */
    public static final Integer OPEN_BATCH_SIZE = 200;

    /**
     * redis锁的默认有效时间，短时间，30s
     */
    public static final Long REDIS_LOCK_EXPIRE_SHORT_SECOND = 30L;

    /**
     * redis锁的默认有效时间，正常时间，60s
     */
    public static final Long REDIS_LOCK_EXPIRE_SECOND = 60L;

    /**
     * redis锁的默认有效时间，长时间，180s
     */
    public static final Long REDIS_LOCK_EXPIRE_LONG_SECOND = 180L;

    /**
     * redis加锁有效时间，默认10s
     */
    public static final Long REDIS_LOCK_PERIOD_TIME = 10L;

    /**
     * redis加锁最大有效时间，15s
     */
    public static final Long REDIS_LOCK_MAX_PERIOD_TIME = 15L;

}

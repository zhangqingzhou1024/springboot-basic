package com.liziyuan.hope.common.utils.date;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 时间转化模式 枚举类型
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-05 21:53
 */
@Getter
@AllArgsConstructor
public enum DateFormatPattern {
    /**
     * yyyy-MM-dd
     */
    DATE_FORMAT("yyyy-MM-dd"),

    /**
     * yyyyMMdd
     */
    DATE_FORMAT_NO_SPLIT("yyyyMMdd"),

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    DATE_TIME_FORMAT("yyyy-MM-dd HH:mm:ss"),

    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    DATE_TIME_MS_FORMAT("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 解析匹配模式
     */
    private String pattern;
}

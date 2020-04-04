

package com.liziyuan.hope.common.utils.validate;


import com.liziyuan.hope.common.utils.DateHelper;

import java.util.Date;

/**
 * 常用默认值判断
 *
 * @author zhangqingzhou
 */
public final class FieldDefaultValueUtils {
    public static final int default_Integer = -1;
    public static final String default_String = "";
    public static final Date default_Date = DateHelper.parseDateTime("0000-00-00 00:00:00");
    public static final Integer MAX_INT = 2147483647;
    public static final Integer MIN_INT = -2147483648;

    public FieldDefaultValueUtils() {
    }

    public static <T> boolean isDefaultOrNull(T value) {
        return value == null ? true : isDefault(value);
    }

    public static <T> boolean isDefault(T value) {
        if (value == null) {
            return false;
        } else if (Integer.class.getName().equals(value.getClass().getName())) {
            return isDefaultInteger((Integer) value);
        } else if (String.class.getName().equals(value.getClass().getName())) {
            return isDefaultString((String) value);
        } else {
            return Date.class.getName().equals(value.getClass().getName()) ? isDefaultDate((Date) value) : false;
        }
    }

    public static boolean isDefaultInteger(Integer value) {
        if (value == null) {
            return false;
        } else {
            return value == -1;
        }
    }

    public static boolean isDefaultLong(Long value) {
        if (value == null) {
            return false;
        } else {
            return value.intValue() == -1;
        }
    }

    public static boolean isMaxInteger(Integer value) {
        return value == null ? false : value.equals(MAX_INT);
    }

    public static boolean isMinInteger(Integer value) {
        return value == null ? false : value.equals(MIN_INT);
    }

    public static boolean isMaxLong(Long value) {
        return value == null ? false : value.equals((long) MAX_INT);
    }

    public static boolean isMinLong(Long value) {
        return value == null ? false : value.equals((long) MIN_INT);
    }

    public static boolean isMinIntegerOrNull(Integer value) {
        return value == null ? true : value.equals(MIN_INT);
    }

    public static boolean isMinLongOrNull(Long value) {
        return value == null ? true : value.equals((long) MIN_INT);
    }

    public static boolean isDefaultString(String value) {
        return value == null ? false : "".equals(value);
    }

    public static boolean isDefaultDate(Date value) {
        return value == null ? false : default_Date.equals(value);
    }
}

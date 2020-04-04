
package com.liziyuan.hope.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
    public static final long TEN_MINUTES = 600000L;
    public static final long ONE_DAY = 86400000L;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_NO_SPLIT = "yyyyMMdd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_MS_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public DateHelper() {
    }

    public static Date add(Date date, Integer field, Integer amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static Date addDate(Date date, Integer days) {
        return add(date, 5, days);
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            if (isEmpty(format)) {
                format = "yyyy-MM-dd";
            }

            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    private static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatDate(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String today() {
        return formatDate(new Date());
    }

    public static String formatDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String now() {
        return formatDateTime(new Date());
    }

    public static String formatDateTimeMs(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public static Date getDateValue(Object object) {
        return null == object ? null : (Date) object;
    }

    public static Date parseDate(String dateString, String format) {
        if (isEmpty(format)) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat(format)).parse(dateString);
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public static Date parseDate(String dateString) {
        return parseDate(dateString, "yyyy-MM-dd");
    }

    public static Date parseDateTime(String dateString) {
        return parseDate(dateString, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date toDate(Long date) {
        return date == null ? null : new Date(date);
    }

    public static long getCurrentTime() {
        return (new Date()).getTime();
    }

    public static String getCurrentDateStr(String dateFormatPattern) {
        if (isEmpty(dateFormatPattern)) {
            dateFormatPattern = "yyyy-MM-dd HH:mm:ss";
        }

        return format(new Date(), dateFormatPattern);
    }

    public static Date getServerTime(String operateTimeStr) {
        Date serverTime = new Date();
        if (isEmpty(operateTimeStr)) {
            return serverTime;
        } else {
            Date operateTime = parseDate(operateTimeStr, "yyyy-MM-dd HH:mm:ss");
            if (operateTime == null) {
                operateTime = parseDate(operateTimeStr, "yyyy-MM-dd HH:mm:ss.SSS");
            }

            Long interval = operateTime.getTime() - serverTime.getTime();
            if (operateTime.after(serverTime) && 600000L < interval) {
                return serverTime;
            } else {
                return operateTime.before(serverTime) && 86400000L < Math.abs(interval) ? serverTime : operateTime;
            }
        }
    }

    public static Date getDateOfStartTime(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            return calendar.getTime();
        }
    }

    public static Date getDateOfEndTime(Date date) {
        if (date == null) {
            return null;
        } else {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
            return calendar.getTime();
        }
    }
}

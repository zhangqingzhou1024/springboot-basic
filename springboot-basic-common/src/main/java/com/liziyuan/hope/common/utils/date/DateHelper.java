package com.liziyuan.hope.common.utils.date;

import com.liziyuan.hope.common.constant.NumberConst;
import com.liziyuan.hope.common.constant.StringConst;
import com.liziyuan.hope.common.constant.SymbolConst;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 时间工具类
 * 用JDK8来实现，线程安全
 * <p>
 * LocalDate：表示与时区无关的日期，与LocalDateTime相比，只有日期信息，没有时间信息
 * LocalDateTime: 表示与时区无关的日期和时间信息，不直接对应时刻，需要通过时区转换
 * LocalTime：表示与时区无关的时间，与LocalDateTime相比，只有时间信息，没有日期信息
 * Instant：它代表的是时间戳，表示时刻，不直接对应年月日信息，需要通过时区转换
 * ZonedDateTime： 表示特定时区的日期和时间
 * ZoneId/ZoneOffset：表示时区
 *
 * @author zqz
 * @version V1.0
 * @date 2020-04-05 20:53
 */
public class DateHelper {
    /**
     * 获取当前时间，时间格式 yyyy-MM-dd HH:mm:ss
     *
     * @return 获取当前时间
     */
    public static String getCurrentTime() {
        // 默认设置格式：yyyy-MM-dd HH:mm:ss
        return DateHelper.getCurrentTimeByPattern(DateFormatPattern.DATE_TIME_FORMAT.getPattern());
    }

    /**
     * 获取当前时间，pattern 为时间格式
     *
     * @param pattern 时间转化格式
     * @return 获取当前时间
     */
    public static String getCurrentTimeByPattern(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTimeFormat(localDateTime, pattern);
    }


    /**
     * 在当前时间的基础上，进行天数想加减
     *
     * @param days 相差的天数
     * @return 计算后的LocalDateTime
     */
    public static LocalDateTime addDaysBaseNow(long days) {
        LocalDateTime now = LocalDateTime.now();
        return now.plusDays(days);
    }

    /**
     * 基于当前月，获取前后几个月的时间
     *
     * @param months 月份差，正负都行
     * @return 计算后的月份时间
     */
    public static LocalDate addMonthsBaseNow(int months) {
        LocalDate localDateTime = LocalDate.now();
        return addMonthBaseNow(months, localDateTime);

    }

    /**
     * 相当于当前时间，进行月份加减，最终返回 加减后的月份数字
     *
     * @param months 月份差，正负都行
     * @return 加减后的月份数字
     */
    public static Integer getMonthNumByMonthGap(int months) {

        LocalDate localDateTime = LocalDate.now();
        LocalDate localDate = addMonthBaseNow(months, localDateTime);

        return localDate.getMonthValue();
    }

    /**
     * 获取满足条件的localDate对象
     *
     * @param months 月度差
     * @return 满足条件的 localDate 对象
     */
    public static LocalDate getLocalDateByMonthGap(int months) {

        LocalDate localDateTime = LocalDate.now();

        return addMonthBaseNow(months, localDateTime);
    }

    /**
     * 获取当前月的年月日期，格式如：2019-08
     *
     * @return 前月的年月日期String格式
     */
    public static String getCurrentYearMonthPattern() {
        return getYearMonthPatternByMonthGap(NumberConst.ZERO);
    }

    /**
     * 相当于当前时间，进行月份加减，最终返回 加减后的年月信息（2019-09）
     *
     * @param months 月份差，正负都行
     * @return 加减后的月份字符 如 2019-09
     */
    public static String getYearMonthPatternByMonthGap(int months) {
        LocalDate localDateTime = LocalDate.now();
        LocalDate localDate = addMonthBaseNow(months, localDateTime);
        int year = localDate.getYear();
        int monthValue = localDate.getMonthValue();

        // 小于10的进行加0处理
        if (monthValue < NumberConst.TEN) {
            return year + SymbolConst.SHORT_LINE + StringConst.ZERO_STRING + +monthValue;
        }
        // 拼接数据返回格式
        return year + SymbolConst.SHORT_LINE + monthValue;
    }

    /**
     * 当前月
     *
     * @param num 相差月份数
     * @return 相差月份结果
     */
    public static LocalDate addMonthBaseNow(int num, LocalDate localDate) {
        if (num == NumberConst.ZERO) {
            return localDate;
        }
        if (num > NumberConst.ZERO) {
            return localDate.plusMonths(num);
        } else {
            return localDate.minusMonths(Math.abs(num));
        }

    }

    /**
     * 根据时间，获取当月的起始时间
     *
     * @param localDate 时间
     * @return 当月的起始时间
     */
    public static LocalDate getFirstDayOfMonth(LocalDate localDate) {

        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 月份开始时间
     *
     * @param months 月份差
     * @return 月份差对应的月份的开始时间
     */
    public static long getStartLongTimeOfMonth(int months) {
        // 定位到月
        LocalDate localDate = addMonthsBaseNow(months);
        // 设置时间
        LocalDateTime localDateTime = LocalDateTime
                .of(localDate.getYear(), localDate.getMonthValue(),
                        1, 0, 0);

        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 根据时间，获取当月的结束时间时间
     *
     * @param localDate 时间
     * @return 当月的结束时间
     */
    public static LocalDate getLastDayOfMonth(LocalDate localDate) {

        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取某月的 最大的天数
     *
     * @param year     年份
     * @param monthVal 月份
     * @return 包含的天数
     */
    public static int getLengthOfMonth(int year, int monthVal) {
        LocalDate targetMonth = LocalDate.of(year, monthVal, NumberConst.ONE);

        // 天的长度
        return targetMonth.lengthOfMonth();
    }

    /**
     * 获取当前年对应某月的最大的天数
     *
     * @param monthVal 月份
     * @return 包含的天数
     */
    public static int getLengthOfMonth(int monthVal) {
        // 天的长度
        return getLengthOfMonth(LocalDate.now().getYear(), monthVal);
    }

    /**
     * 根据月份差获取对应月的起始时间，string 格式
     *
     * @param months 月份差
     * @return 月份差对应的月份的的起始时间
     */
    public static String getStartTimeOfMonthFirstDay(int months) {
        LocalDate localDate = addMonthsBaseNow(months);
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());

        // 返回形式构造
        return firstDayOfMonth + StringConst.START_TIME_SUFFIX;
    }

    /**
     * 根据月份差，获取对应月的结束时间
     *
     * @param months 时间差
     * @return 对应月的结束时间
     */
    public static String getEndTimeOfMonthLastDay(int months) {
        LocalDate localDate = addMonthsBaseNow(months);
        LocalDate endDay = localDate.with(TemporalAdjusters.lastDayOfMonth());

        // 返回形式构造
        return endDay + StringConst.END_TIME_SUFFIX;
    }


    /**
     * 计算两段时间的间隔 时间间隔为天，允许出现负数
     *
     * @param lowerTime  较早的时间
     * @param higherTime 较晚的时间
     * @return 时间间隔，单位天
     */
    public static BigDecimal calculateTimeBetween(Date lowerTime, Date higherTime) {
        if (lowerTime == null || higherTime == null) {
            return null;
        }
        long lowerCurr = lowerTime.getTime();
        long higherCurr = higherTime.getTime();
        long timeBetweenCurr = higherCurr - lowerCurr;


        return BigDecimal.valueOf(timeBetweenCurr).divide(BigDecimal.valueOf(DateUtils.MILLIS_PER_DAY), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 获取当前时间所在的月份
     * the month-of-year, from 1 to 12
     *
     * @param date date对象
     * @return 月份值
     */
    public static Integer getMonthNum(Date date) {
        if (null == date) {
            throw new NullPointerException("解析的日期参数不可为空！");
        }
        return dateToLocalDate(date).getMonthValue();
    }

    /**
     * 获取getDayOfMonth
     *
     * @param date date对象
     * @return 日期值
     */
    public static Integer getDayOfMonth(Date date) {
        if (null == date) {
            throw new NullPointerException("解析的日期参数不可为空！");
        }
        return dateToLocalDate(date).getDayOfMonth();
    }

    /**
     * 将date转换成String
     *
     * @param date Date对象
     * @return string 类型的时间
     */
    public static String dateFormat(Date date) {
        return dateFormat(date, DateFormatPattern.DATE_TIME_FORMAT.getPattern());
    }

    /**
     * 将date转换成String
     *
     * @param date    Date对象
     * @param pattern 解析模式
     * @return string 类型的时间
     */
    public static String dateFormat(Date date, String pattern) {
        return localDateTimeFormat(dateToLocalDateTime(date), pattern);
    }

    /**
     * 将localDate 按照一定的格式转换成String
     *
     * @param localDate LocalDate对象
     * @param pattern   解析模式
     * @return string 类型的时间
     */
    public static String localDateFormat(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将localDateTime 按照一定的格式转换成String
     *
     * @param localDateTime localDateTime
     * @param pattern       解析模式
     * @return string 类型的时间
     */
    public static String localDateTimeFormat(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 从字符型转化到 date
     *
     * @param time 字符型时间 yyyy-MM-dd HH:mm:ss 格式
     * @return date 类型
     */
    public static Date dateParse(String time) {
        LocalDateTime localDateTime = localDateTimeParse(time, DateFormatPattern.DATE_TIME_FORMAT.getPattern());
        return localDateTimeToDate(localDateTime);
    }

    /**
     * 从字符型转化到 date
     *
     * @param time    字符型时间
     * @param pattern 格式化形式
     * @return date 类型
     */
    public static Date dateParse(String time, String pattern) {
        LocalDateTime localDateTime = localDateTimeParse(time, pattern);
        return localDateTimeToDate(localDateTime);
    }

    /**
     * 将String 按照pattern 转换成
     *
     * @param time    string 类型的时间
     * @param pattern 解析模式
     * @return LocalDate
     */
    public static LocalDate localDateParse(String time, String pattern) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将String 按照pattern 转换成LocalDateTime
     *
     * @param time    string 类型的时间
     * @param pattern 解析模式
     * @return LocalDateTime
     */
    public static LocalDateTime localDateTimeParse(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将LocalDate 转换成 Date
     *
     * @param localDate LocalDate对象
     * @return LocalDate 转换成 Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将LocalDateTime 转换成 Date
     *
     * @param localDateTime LocalDateTime对象
     * @return Date对象
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 将 Date 转换成LocalDate
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date date对象
     * @return Date 转换成LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }

    /**
     * 将 Date 转换成LocalDateTime
     * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
     *
     * @param date date对象
     * @return Date 转换成LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 计算两个LocalDateTime 之间的毫秒数
     *
     * @param time1 LocalDateTime对象
     * @param time2 LocalDateTime对象
     * @return 两个LocalDateTime 之间的毫秒数
     */
    public static Long minusToMillsLocalDateTime(LocalDateTime time1, LocalDateTime time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalTime 之间的毫秒数
     *
     * @param time1 LocalTime对象
     * @param time2 LocalTime对象
     * @return 两个LocalTime 之间的毫秒数
     */
    public static Long minusToMillsLocalTime(LocalTime time1, LocalTime time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalDate 之间的毫秒数
     *
     * @param time1 LocalDate对象
     * @param time2 LocalDate对象
     * @return 两个LocalDate 之间的毫秒数
     */
    public static Long minusToMillsLocalDate(LocalDate time1, LocalDate time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 计算两个LocalDate 之间的Period
     *
     * @param time1 LocalDate对象
     * @param time2 LocalDate对象
     * @return 两个LocalDate 之间的Period
     */
    public static Period periodLocalDate(LocalDate time1, LocalDate time2) {
        return Period.between(time1, time2);
    }

    /**
     * 计算两个Date 之间的Period
     *
     * @param date1 Date对象
     * @param date2 Date对象
     * @return 两个Date 之间的Period
     */
    public static Period periodDate(Date date1, Date date2) {
        return periodLocalDate(dateToLocalDate(date1), dateToLocalDate(date2));
    }

    /**
     * 计算两个Date之间的 毫秒数
     *
     * @param time1 Date对象
     * @param time2 Date对象
     * @return 两个Date之间的 毫秒数
     */
    public static Long minusToMillsDate(Date time1, Date time2) {
        return minusToMillsLocalDateTime(dateToLocalDateTime(time1), dateToLocalDateTime(time2));
    }

}

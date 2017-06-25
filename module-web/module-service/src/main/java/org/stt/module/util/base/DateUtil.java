package org.stt.module.util.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类
 */
public class DateUtil {

    private static final String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    private static final String HHMM = "HH:mm";
    
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * 1秒钟的毫秒数
     */
    public static final long MILLIS_PER_SECOND = 1000;

    /**
     * 1分钟的毫秒数
     */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

    /**
     * 1小时的毫秒数
     */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    /**
     * 1天的毫秒数
     */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    /**
     * 获取当前月的起始时间，如：2012-09-01 00:00:00.000
     * 
     * @return 当前月的起始时间
     */
    public static Date getStartOfMonth() {

        return getStartOfMonth(0);
    }

    /**
     * 获取当前月距amount个月的起始时间，如：2012-09月前一个月（amount = -1）的起始日期：2012-08-01
     * 00:00:00.000
     * 
     * @param amount 间隔月份
     * @return 当前月之前amount个月的起始时间
     */
    public static Date getStartOfMonth(int amount) {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, amount);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();

    }

    /**
     * 获取当前月的结束时间，如：2012-09-30 23:59:59.999
     * 
     * @return 当前月的结束时间
     */
    public static Date getEndOfMonth() {

        return getEndOfMonth(0);
    }

    /**
     * 获取距当前月amount个月的结束时间，如：2012-09月前一个月（amount = 1）的起始日期：2012-08-31
     * 23:59:59.999
     * 
     * @param amount 间隔月份
     * @return 距当前月amount个月的结束时间
     */
    public static Date getEndOfMonth(int amount) {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, amount + 1);
        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 时间字符串转化为日期类型，格式为yyyy-MM-dd HH:mm:ss
     * 
     * @param dateTime 时间字符串，格式为yyyy-MM-dd HH:mm:ss
     * @return Date
     */
    public static Date parseDateTime(String dateTime) {

        try {
            return new SimpleDateFormat(YYYY_MM_DD_HHMMSS).parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 时间字符串转化为日期类型，格式为yyyy-MM-dd
     * 
     * @param dateTime 时间字符串，格式为yyyy-MM-dd
     * @return Date
     */
    public static Date parseDate(String date) {

        try {
            return new SimpleDateFormat(YYYY_MM_DD).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 时间字符串转化为日期类型，格式为HH:mm
     * 
     * @param dateTime 时间字符串，格式为HH:mm
     * @return Date
     */
    public static Date parseTime(String date) {

        try {
            return new SimpleDateFormat(HHMM).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 格式化日期类型为字符串，格式为yyyy-MM-dd HH:mm:ss
     * 
     * @param dateTime 日期类型
     * @return 时间字符串，格式为yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date dateTime) {

        if (dateTime == null) {
            return null;
        }
        return new SimpleDateFormat(YYYY_MM_DD_HHMMSS).format(dateTime);
    }

    /**
     * 格式化日期类型为字符串，格式为yyyy-MM-dd
     * 
     * @param dateTime 日期类型
     * @return 时间字符串，格式为yyyy-MM-dd
     */
    public static String formatDate(Date date) {

        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(YYYY_MM_DD).format(date);
    }

    /**
     * 
     * @return 当前时间
     */
    public static Date getCurrentTime() {

        return Calendar.getInstance().getTime();
    }

    /**
     * 
     * @param dayAmount
     * @return
     */
    public static Date afterNowOfDay(int dayAmount) {

        return addDays(Calendar.getInstance().getTime(), dayAmount);
    }

    /**
     * 获取两个日期之间的天数
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getBetweenDays(Date startDate, Date endDate) {

        if (startDate == null || endDate == null) {
            return null;
        }
        Long day = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if two date objects are on the same day ignoring time.
     * </p>
     * 
     * <p>
     * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true. 28 Mar 2002
     * 13:45 and 12 Mar 2002 13:45 would return false.
     * </p>
     * 
     * @param date1 the first date, not altered, not null
     * @param date2 the second date, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either date is <code>null</code>
     * @since 2.1
     */
    public static boolean isSameDay(Date date1, Date date2) {

        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * <p>
     * Checks if two calendar objects are on the same day ignoring time.
     * </p>
     * 
     * <p>
     * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true. 28 Mar 2002
     * 13:45 and 12 Mar 2002 13:45 would return false.
     * </p>
     * 
     * @param cal1 the first calendar, not altered, not null
     * @param cal2 the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     * @since 2.1
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {

        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
                .get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * Adds a number of months to a date returning a new object. The original
     * date object is unchanged.
     * 
     * @param date the date, not null
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addMonths(Date date, int amount) {

        return add(date, Calendar.MONTH, amount);
    }

    /**
     * Adds a number of days to a date returning a new object. The original date
     * object is unchanged.
     * 
     * @param date the date, not null
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date addDays(Date date, int amount) {

        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * Adds to a date returning a new object. The original date object is
     * unchanged.
     * 
     * @param date the date, not null
     * @param calendarField the calendar field to add to
     * @param amount the amount to add, may be negative
     * @return the new date object with the amount added
     * @throws IllegalArgumentException if the date is null
     */
    public static Date add(Date date, int calendarField, int amount) {

        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static String now() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(Calendar.getInstance().getTime());
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 加减指定的日期的年份。
     * @param date --指定的日期
     * @param amount --数量可以为负数
     * @return 计算后的结果
     */
    public static Date addYears(Date date, int amount) {

        return add(date, Calendar.YEAR, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 加减指定的日期的小时数
     * @param date --指定的日期
     * @param amount --加减数量
     * @return 计算后的结果
     */
    public static Date addHours(Date date, int amount) {

        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 加减指定的日期的分钟数
     * @param date --指定的日期
     * @param amount --加减数量
     * @return 计算后的结果
     */
    public static Date addMinutes(Date date, int amount) {

        return add(date, Calendar.MINUTE, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 加减指定的日期的秒数
     * @param date --指定的日期
     * @param amount --加减数量
     * @return 计算后的结果
     */
    public static Date addSeconds(Date date, int amount) {

        return add(date, Calendar.SECOND, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 加减指定的日期的毫秒数
     * @param date --指定的日期
     * @param amount --加减数量
     * @return 计算后的结果
     */
    public static Date addMilliseconds(Date date, int amount) {

        return add(date, Calendar.MILLISECOND, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 设置指定日期重新的年份
     * @param date --指定的日期
     * @param amount --年份
     * @return 设置后的日期
     */
    public static Date setYears(Date date, int amount) {

        return set(date, Calendar.YEAR, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 设置指定日期的月份
     * @param date --指定的日期
     * @param amount --月份
     * @return 设置后的日期
     */
    public static Date setMonths(Date date, int amount) {

        return set(date, Calendar.MONTH, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 设置指定日期的天数
     * @param date --指定的日期
     * @param amount --天数
     * @return 设置后的日期
     */
    public static Date setDays(Date date, int amount) {

        return set(date, Calendar.DAY_OF_MONTH, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 设置指定日期的小时
     * @param date --指定日期
     * @param amount --小时数
     * @return 设置后的日期
     */
    public static Date setHours(Date date, int amount) {

        return set(date, Calendar.HOUR_OF_DAY, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 设置指定日期的分钟数
     * @param date --指定日期
     * @param amount --分钟数
     * @return 设置后的日期
     */
    public static Date setMinutes(Date date, int amount) {

        return set(date, Calendar.MINUTE, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 设置指定日期的秒
     * @param date --指定日期
     * @param amount --秒
     * @return 设置后的日期
     */
    public static Date setSeconds(Date date, int amount) {

        return set(date, Calendar.SECOND, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 设置指定日期的毫秒
     * @param date --指定日期
     * @param amount --毫秒
     * @return 设置后的日期
     */
    public static Date setMilliSeconds(Date date, int amount) {

        return set(date, Calendar.MILLISECOND, amount);
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 设置指定日期的各域
     * @param date --指定日期
     * @param calendarFiled --域包括年、月、日、时、分、秒、毫秒
     * @param amount --数量
     * @return 设置后日期
     */
    public static Date set(Date date, int calendarFiled, int amount) {

        if (null == date) {
            throw new IllegalArgumentException("The date must not be null");
        } else {

        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(calendarFiled, amount);
        return c.getTime();
    }

    // ---------------------------------------------------------------------------------------------------
    /**
     * 把日期转换成日历
     * @param date --日期格式
     * @return 日历格式
     */
    public static Calendar toCalendar(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    // ----------------------------------------------------------------------------------------------------
    /**
     * 两个日期相加
     * @param d1 --日期1
     * @param d2 --日期2
     * @return 相加后的日期
     */
    public static Date addTwo(Date d1, Date d2) {

        if (null == d1 || null == d2) {
            return null;
        } else {

        }
        return new Date(d1.getTime() + d2.getTime());
    }

    // -----------------------------------------------------------------------------------------------------
    /**
     * 计算两个日期之间相差的年，不足一年的被舍去，d1和d2都不能为null
     * @param d1 --日期1
     * @param d2 --日期2
     * @return d1和d2相差的年份
     */
    public static int minusToYear(Date d1, Date d2) {

        if (null == d1 || null == d2) {
            throw new java.lang.IllegalArgumentException("d1和d2都不能为null");
        } else {

        }
        Calendar cl1 = Calendar.getInstance();
        Calendar cl2 = Calendar.getInstance();
        cl1.setTime(d1);
        cl2.setTime(d2);
        return cl1.get(Calendar.YEAR) - cl2.get(Calendar.YEAR);
    }

    // ----------------------------------------------------------------------------------------------------
    /**
     * 计算两个日期之间的月份，不足一月的被舍去，d1和d2都不能为null
     * @param d1 --日期1
     * @param d2 --日期2
     * @return d1和d2之间相差月份
     */
    public static int minusToMonth(Date d1, Date d2) {

        if (null == d1 || null == d2) {
            throw new java.lang.IllegalArgumentException("d1和d2都不能为null");
        } else {

        }
        Calendar cl1 = Calendar.getInstance();
        Calendar cl2 = Calendar.getInstance();
        cl1.setTime(d1);
        cl2.setTime(d2);
        return (cl1.get(Calendar.YEAR) - cl2.get(Calendar.YEAR)) * 12 + cl1.get(Calendar.MONTH)
                - cl2.get(Calendar.MONTH);
    }

    // ----------------------------------------------------------------------------------------------------
    /**
     * 计算两个日期之间的天数，不足一天的被舍去，d1和d2都不能为null
     * @param d1 --日期1
     * @param d2 --日期2
     * @return d1和d2之间相差的天数
     */
    public static int minusToDay(Date d1, Date d2) {

        return (int) (minusToMilliSecond(d1, d2) / MILLIS_PER_DAY);
    }

    // ----------------------------------------------------------------------------------------------------
    /**
     * 计算两个日期之间的小时数，不足一小时的被舍去，d1和d2都不能为null
     * @param d1 --日期1
     * @param d2 --日期2
     * @return d1和d2之间相差的小时数
     */
    public static int minusToHours(Date d1, Date d2) {

        return (int) (minusToMilliSecond(d1, d2) / MILLIS_PER_HOUR);
    }

    // ----------------------------------------------------------------------------------------------------
    /**
     * 计算两个日期之间的分钟数不足一分钟的被舍去，d1和d2都不能为null
     * @param d1 --日期1
     * @param d2 --日期2
     * @return d1和d2之间相差的分钟数
     */
    public static long minusToMinutes(Date d1, Date d2) {

        return minusToMilliSecond(d1, d2) / MILLIS_PER_MINUTE;
    }

    // ----------------------------------------------------------------------------------------------------
    /**
     * 计算两个日期之间的秒数不足以秒的被舍去，d1和d2都不能为null
     * @param d1 --日期1
     * @param d2 --日期2
     * @return d1和d2之间相差的秒数
     */
    public static long minusToSeconds(Date d1, Date d2) {

        return minusToMilliSecond(d1, d2) / MILLIS_PER_SECOND;
    }

    // -----------------------------------------------------------------------------------------------------
    /**
     * 计算两个日期之间的毫秒数，d1和d2都不能为null
     * @param d1 --日期1
     * @param d2 --日期2
     * @return d1和d2之间相差的毫秒数
     */
    public static long minusToMilliSecond(Date d1, Date d2) {

        if (null == d1 || null == d2) {
            throw new java.lang.IllegalArgumentException("d1和d2都不能为null");
        } else {

        }
        return d1.getTime() - d2.getTime();
    }

    /**
     * 将string转换为date 入参格式like：“Wed Mar 19 13:20:02 CST 2014”
     * @param date
     * @return
     */
    public static Date formatDate(String date) {

        if (null == date) {
            return null;
        } else {
            Long longDate = Date.parse(date);
            Date newDate = new Date(longDate - (14 * MILLIS_PER_HOUR));
            return newDate;
        }
    }

    /**
     * 获取下天日期
     * 
     * @param specifiedDay
     * @return
     * @throws ParseException
     */
    public static Date getNextDay(Date specifiedDay) throws ParseException {

        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        String dayAfter = new SimpleDateFormat(YYYY_MM_DD).format(c.getTime());
        Date nextDay = new SimpleDateFormat(YYYY_MM_DD).parse(dayAfter);
        return nextDay;
    }

    /**
     * 获取当前时间的n年之后的日期
     * @param 年数
     * @return
     * @throws ParseException
     */
    public static Date getNextNyear(Date nowTime, int n) throws ParseException {

        Calendar c = Calendar.getInstance();
        c.setTime(nowTime);
        int year = c.get(Calendar.YEAR);
        c.set(Calendar.YEAR, year + n);
        String yearAfter = new SimpleDateFormat(YYYY_MM_DD).format(c.getTime());
        Date nextNyear = new SimpleDateFormat(YYYY_MM_DD).parse(yearAfter);
        return nextNyear;
    }

    /**
     * 获取下天日期
     * 
     * @param specifiedDay
     * @return
     * @throws ParseException
     */
    public static Date getNextNDay(Date specifiedDay, int n) throws ParseException {

        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + n);
        String dayAfter = new SimpleDateFormat(YYYY_MM_DD_HHMMSS).format(c.getTime());
        Date nextDay = new SimpleDateFormat(YYYY_MM_DD_HHMMSS).parse(dayAfter);
        return nextDay;
    }

    /**
     * 获取指定当天的23:59:59
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getEndTimeOfDay(Date date) throws ParseException {

        String tmpDate = new SimpleDateFormat(YYYY_MM_DD).format(date);
        tmpDate = tmpDate + " 23:59:59";
        Date endDate = new SimpleDateFormat(YYYY_MM_DD_HHMMSS).parse(tmpDate);
        return endDate;
    }
    
    /**
     * 
     * @param date
     * @param formatString
     * @return
     */
    public static String dateToString(Date date, String formatString) {
        if (date == null) {
            return null;
        }
        if(StringUtils.isEmpty(formatString)) {
            formatString = YYYYMMDD;
        }
        return new SimpleDateFormat(formatString).format(date);
    }
}

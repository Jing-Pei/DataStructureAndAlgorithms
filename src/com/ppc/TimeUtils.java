package com.ppc;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 时间工具类
 */
public class TimeUtils {
    
    private static final Long DAYINMILLISECONDS = 1000 * 60 * 60 * 24l; //一天的毫秒数
    private static final Long HOURINMILLISECONDS = 60 * 60 * 1000L ; //一小时的毫秒数
    
    /**
     * 格式为 yyyyMMddhhmmss 返回值为long类型的当前时间
     */
    public static long getNow() {
        Date date = new Date();
        return parseLong(date);
    }

    /**
     * 将指定日期转换为yyyyMMddHHmmss格式
     */
    public static Long parseLong(Date date) {
        if (null == date) {
            return 0l;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(date);
        return Long.parseLong(format);
    }

    public static Long getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentDate = new Date();
        String time = dateFormat.format(currentDate);
        return Long.parseLong(time);
    }

    public static String getCurrentTimeDay2() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String time = dateFormat.format(currentDate);
        return time;
    }

    public static Long getCurrentTimeDay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();
        String time = dateFormat.format(currentDate);
        return Long.parseLong(time);
    }

    public static Date getDateFromTimeDay(String timeDay) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = dateFormat.parse(timeDay);
        return date;
    }
    
    /**
     * 
     * @param date1
     * @param date2
     * @return date1 date2的间隔天数
     */
    public static int getDaysBetweenDates(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (DAYINMILLISECONDS));
    }

    // 获取本月的第零天
    public static Long getCurrentTimeMonth() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMM");
        String format = now.format(yyyyMMdd);
        String s = format + "00";
        return Long.parseLong(s);
    }

    public static Long getTimeDay(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        String time = dateFormat.format(date);
        return Long.parseLong(time);
    }

    /**
     * String 类型转化为8位long ，基于字符串的操作
     */
    public static Long stringParse8Long2(String s) {
        String substring = s.substring(0, 10);
        String replace = substring.replace("-", "");
        return Long.parseLong(replace.trim());
    }

    /**
     * 获取两个时间相差的天数
     * 
     * @param fDate
     * @param oDate
     * @return
     */
    public static int getIntervalDays(Date fDate, Date oDate) {

        if (null == fDate || null == oDate) {

            return -1;
        }

        long intervalMilli = oDate.getTime() - fDate.getTime();

        return (int) (intervalMilli / (DAYINMILLISECONDS));

    }

    /**
     * 根据入职天数计算工龄
     * 
     * @param i
     * @return
     */
    public static BigDecimal getWorkingYears(int i) {
        BigDecimal bigDecimal = new BigDecimal(i);
        BigDecimal yearDay = new BigDecimal("365");
        BigDecimal divide = bigDecimal.divide(yearDay, 1, BigDecimal.ROUND_HALF_UP);
        return divide;
    }

    /**
     * Long类型转化为String类型，基于时间的操作 12小时制
     * 
     * @param s
     * @return
     * @throws ParseException
     */
    public static String longParse8String(Long time, int length) throws ParseException {
        String str;
        if (time.toString().length() < 8) {
            return "";
        } else if (time.toString().length() >= length) {
            str = time.toString().substring(0, length);
        } else {
            str = time.toString();
        }
        SimpleDateFormat sdf1;
        SimpleDateFormat sdf2;
        if (length == 8) {
            sdf1 = new SimpleDateFormat("yyyyMMdd");
            sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        } else if (length == 12) {
            sdf1 = new SimpleDateFormat("yyyyMMddhhmm");
            sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        } else {
            sdf1 = new SimpleDateFormat("yyyyMMddhhmmss");
            sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
        Date parse = sdf1.parse(str);
        String s = sdf2.format(parse);
        return s;
    }

    /**
     * long转String 24小时制
     * @param time
     * @param length
     * @return
     * @throws ParseException
     */
    public static String long2String(Long time, int length) throws ParseException {
        String str;
        if (time.toString().length() < 8) {
            return "";
        } else if (time.toString().length() >= length) {
            str = time.toString().substring(0, length);
        } else {
            str = time.toString();
        }
        SimpleDateFormat sdf1;
        SimpleDateFormat sdf2;
        if (length == 8) {
            sdf1 = new SimpleDateFormat("yyyyMMdd");
            sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        } else if (length == 12) {
            sdf1 = new SimpleDateFormat("yyyyMMddHHmm");
            sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else {
            sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
            sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        Date parse = sdf1.parse(str);
        String s = sdf2.format(parse);
        return s;
    }

    /**
     * Get a diff between two dates
     * 
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * 返回两个long型时间之间的小时差，精确到小数点后两位后
     * 
     * @param l1
     * @param l2
     * @return
     * @throws ParseException
     */
    public static BigDecimal getHoursBetweenLongDates(Long l1, Long l2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date1 = sdf.parse(l1.toString());
        Date date2 = sdf.parse(l2.toString());
        BigDecimal time = new BigDecimal(date2.getTime() - date1.getTime())
                .divide(new BigDecimal(HOURINMILLISECONDS), 2, BigDecimal.ROUND_DOWN);
        return time;
    }

    /**
     * 判断日期是否是当月
     * 
     * @param l
     * @return
     * @throws ParseException
     */
    public static boolean isNowMonth(Long l) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(l.toString());
        Calendar calendar;
        Date firstDate, lastDate;
        // 当月第一天
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        firstDate = sdf.parse(sdf.format(calendar.getTime()));
        // 当月最后一天
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        lastDate = sdf.parse(sdf.format(calendar.getTime()));
        if (firstDate.compareTo(date) <= 0 && date.compareTo(lastDate) <= 0)
            return true;
        return false;
    }

    /**
     *
     * @param durationType 1：本周 2：本月 3：本季度 4：今年 5：去年 6：上月
     * @param num
     * @param dayType 1：第一天 2：最后一天
     * @return
     */
    public static Long getDateByDurationType(int durationType, int dayType) {
        Long date;
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH));
        if (durationType == 1 && dayType == 1) {
            // 本周第一天
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else if (durationType == 1 && dayType == 2) {
            // 本周最后一天
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.add(Calendar.DAY_OF_WEEK, 6);
        } else if (durationType == 2 && dayType == 1) {
            // 本月第一天
            cal.set(Calendar.DAY_OF_MONTH, 1);
        } else if (durationType == 2 && dayType == 2) {
            // 本月最后一天
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DAY_OF_MONTH, 0);
        } else if (durationType == 3 && dayType == 1) {
            // 本季度第一天
            int month = getQuarterInMonth(cal.get(Calendar.MONTH), true);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DAY_OF_MONTH, 1);
        } else if (durationType == 3 && dayType == 2) {
            // 本季度最后一天
            int month = getQuarterInMonth(cal.get(Calendar.MONTH), false);
            cal.set(Calendar.MONTH, month + 1);
            cal.set(Calendar.DAY_OF_MONTH, 0);
        } else if (durationType == 4 && dayType == 1) {
            // 今年第一天
            cal.set(Calendar.DAY_OF_YEAR, 1);
        } else if (durationType == 4 && dayType == 2) {
            // 今年最后一天
            cal.set(Calendar.DAY_OF_YEAR, 0);
            cal.add(Calendar.YEAR, 1);
        } else if (durationType == 5 && dayType == 1) {
            // 去年第一天
            cal.add(Calendar.YEAR, -1);
            cal.set(Calendar.DAY_OF_YEAR, 1);
        } else if (durationType == 5 && dayType == 2) {
            // 去年最后一天
            cal.set(Calendar.DAY_OF_YEAR, 0);
        } else if (durationType == 6 && dayType == 1) {
            // 上月第一天
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
        } else if (durationType == 6 && dayType == 2) {
            // 上月最后一天
            cal.set(Calendar.DAY_OF_MONTH, 0);
        }
        date = getTimeDay(cal.getTime());
        return date;
    }

    /**
     * 返回本季度月份
     * 
     * @param month
     * @param isQuarterStart
     * @return
     */
    private static int getQuarterInMonth(int month, boolean isQuarterStart) {
        int months[] = {0, 3, 6, 9};
        if (!isQuarterStart) {
            months = new int[] {2, 5, 8, 11};
        }
        if (month >= 0 && month <= 2)
            return months[0];
        else if (month >= 3 && month <= 5)
            return months[1];
        else if (month >= 6 && month <= 8)
            return months[2];
        else
            return months[3];
    }

    /**
     * 根据年月获取月初或月末
     * @param date YYYYMM
     * @param m 月份加m
     * @param isFirst TRUE/FALSE
     * @return
     */
    public static Long getDayOfMonth(String date, int m, boolean isFirst) {
        if (date.length() < 6) return null;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)));
        cal.add(Calendar.MONTH, m);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        if (isFirst) cal.set(Calendar.DAY_OF_MONTH, 1);
        Date day = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(day);
        long l = Long.parseLong(format);
        return l;
    }

    public static Long getThisWeekDay( int i) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, i);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(date);
        long l = Long.parseLong(format);
        return l;
    }

    public static String getWeekDays(Long l) throws ParseException {
        SimpleDateFormat yyyymmdd = new SimpleDateFormat("yyyyMMdd");
        Date parse = yyyymmdd.parse(l.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(parse);
        return week;
    }


    /**
     * 计算两个天数之差
     * 
     * @param day1
     * @param day2
     * @return
     * @throws ParseException
     */
    public static Integer getDaysDiff(Long day1, Long day2) throws ParseException {
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        long time = yyyyMMdd.parse(day1.toString()).getTime();
        long time2 = yyyyMMdd.parse(day2.toString()).getTime();
        return (int) ((time2 - time) / (1000 * 3600 * 24));
    }

    /*
     * 获取与当前日期间隔num天的日期
     */
    public static Long getTimeAfterDays(Integer days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);// 让日期加days
        return Long.parseLong(format.format(calendar.getTime()));
    }

    /*
     * 获取与某日期间隔n天的日期
     */
    public static Long getTimeAfterDays(Long time, Integer n) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = format.parse(time.toString());
        Date dateNew = new Date(date.getTime() + n * DAYINMILLISECONDS);
        Long time2 = Long.parseLong(format.format(dateNew));
        return time2;
    }

    /*
     * 获取当前日期num天前的日期
     */
    public static Long getTimeBeforeDays(Integer days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);// 让日期减days
        return Long.parseLong(format.format(calendar.getTime()));
    }

    /**
     * 获取指定时间的时间戳
     * 
     * @param time
     * @param originFormat 原格式
     * @param targetFormat 目标格式
     * @return 指定格式的时间戳
     */
    public static Object getTimeStamp(String time, String originFormat, String targetFormat)
            throws ParseException {
        SimpleDateFormat origin = new SimpleDateFormat(originFormat);
        SimpleDateFormat target = new SimpleDateFormat(targetFormat);
        Date data = origin.parse(time);
        return target.format(data);
    }

    /**
     * 给指定日期偏移指定日期 格式 yyyyMMdd offset 正数加 负数减
     */
    public static int plusDate(int date, int offset, DateMode mode) {
        DateTime dateTime = getDateTime(date);
        DateTime dt;
        switch (mode) {
            case DAY:
                dt = dateTime.plusDays(offset);
                break;
            case WEEK: //这个是给当前日期偏移7天 
                dt =dateTime.plusWeeks(offset);
                break;
            case MONTH:
                dt = dateTime.plusMonths(offset);
                break;
            case YEAR:
                dt = dateTime.plusYears(offset);
                break;
            default:
                dt = dateTime;
                break;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(sdf.format(dt.toDate()));
    }
    
    public static DateTime getDateTime(int date) {
        int year = date / 10000;
        int month = date % 10000 / 100;
        int day = date % 10000 % 100;
        return new DateTime(year, month, day, 0, 0);
    }

    public static String getCurrentTimeStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentDate = new Date();
        String time = dateFormat.format(currentDate);
        return time;
    }

    /**
     * 日期类型
     */
    public static enum DateMode {
        DAY("DAY", -30), WEEK("WEEK", -24), MONTH("MONTH", -24), YEAR("YEAR", -5);
        private final String type;
        private final int timeSpan;

        private DateMode(String type, Integer timeSpan) {
            this.type = type;
            this.timeSpan = timeSpan;
        }

        public int getTimeSpan() {
            return timeSpan;
        }

        public String getType() {
            return type;
        }

    }
    
    /**
     * 给指定日期所在周的周几 以周一为一周的第一天
     */
    public static int getDayOfWeek(int date) {
        DateTime dateTime = getDateTime(date);
        return dateTime.getDayOfWeek();
    }
    
    /**
     * 给指定日期所在月的第几天 
     */
    public static int getDayOfMonth(int date) {
        DateTime dateTime = getDateTime(date);
        return dateTime.getDayOfMonth();
    }

    /**
     * 根据当前时间获取上个月月份yyyyMM
     */
    public static String getLastMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(c.getTime());
    }

    /**
     * 获取月份
     */
    public static String getMonthByAmount(Integer amount) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, amount);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(c.getTime());
    }

    /**
     * 获取当前时间，精确到分钟
     */
    public static Long getCurrentTimeMinute() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        Date currentDate = new Date();
        String time = dateFormat.format(currentDate);
        return Long.parseLong(time);
    }
}

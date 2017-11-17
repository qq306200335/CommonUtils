package com.xiaobai.utils.common;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 获取时间工具类
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyyMMdd
     */
    public static String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy/MM/dd
     */
    public static String getDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyyMMddHHmmss
     */
    public static String getNowTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = formatter.format(currentTime);
        return time;
    }

    /**
     * 获取现在时间
     *
     * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(currentTime);
        return time;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String time = formatter.format(currentTime);
        return time;
    }

    /**
     * 获取指定日期的时间 小时:分 HH:mm
     *
     * @return
     */
    public static String getDateOfTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 转换为字符串 yyyy-MM-dd
     *
     * @param year  年
     * @param month 月
     * @param day   天
     * @return
     */
    public static String intFormatToDateStr(int year, int month, int day) {
        String parten = "00";
        DecimalFormat decimal = new DecimalFormat(parten);
        String dateStr = year + "-" +
                decimal.format(month) + "-" + decimal.format(day);
        return dateStr;
    }

    /**
     * 将字符串日期转换为long类型
     *
     * @param date
     * @return
     * @throws ParseException
     * @throws Exception
     */
    public static Long strDateToLong(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt = sdf.parse(date);
            return dt.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将10位数毫秒转换为详细时间
     */
    public static String getDateTime(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(c.getTime());
    }

    /**
     * 将10位数毫秒转换为日期
     */
    public static String getDate(String time) {
        if (time.length() < 10) {
            return "0";
        }
        long timel = Long.parseLong(time);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timel * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    /**
     * 将13位毫秒转换为日期
     */
    public static String getLongDate(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(c.getTime());
    }

    /**
     * 得到下个月具体时间还款日期的时间戳
     */
    public static long getNextMonth() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 2;
        int year = c.get(Calendar.YEAR);
        String date = year + "-" + month + "-16 00:00:00";
        long time = strDateToLong(date);
        return time / 1000;
    }

    /**
     * 得到添加月份的时间
     *
     * @param month
     * @return
     */
    public static String getAddMonth(int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return sdf.format(c.getTime());
    }

    /**
     * 得到添加月份的时间戳
     *
     * @param month
     * @return
     */
    public static long getMonthTime(int month) {
        long time = strDateToLong(getAddMonth(month));
        return time / 1000;
    }

    /**
     * 返回13位月份时间戳
     *
     * @param month
     * @return
     */
    public static long getMonthLongTime(int month) {
        return strDateToLong(getAddMonth(month));
    }

    /**
     * 根据时间戳得到月份和年
     *
     * @param time
     * @return
     */
    public static String getCurrYearMonth(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        return sdf.format(c.getTime());
    }

    /**
     * 根据时间戳得到年月日
     *
     * @param time
     * @return
     */
    public static String getCurrYearMonthDay(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(c.getTime());
    }

    /**
     * 得到当前时间的月份和年
     */
    public static int getThisYearMonth() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        c.get(Calendar.YEAR);
        c.get(Calendar.MONTH);
        return Integer.parseInt(sdf.format(c.getTime()));
    }

    /**
     * 得到添加月份的月份
     *
     * @param month 月份
     * @return
     */
    public static String getMonth(int month) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, month);
        return c.get(Calendar.MONTH) + 1 + "";
    }

    /**
     * 得到添加月份的年月
     *
     * @param month 月份
     * @return
     */
    public static String getYearMonth(int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, month);
        return sdf.format(c.getTime());
    }

    /**
     * 获取当前时间戳
     */
    public static long getLongTime() {
        return System.currentTimeMillis();
    }

    /**
     * 得到当前日期的去年的时间
     *
     * @return yyyy-MM-dd
     */
    public static String getLastYear(Calendar calendar) {

        calendar.add(Calendar.YEAR, -1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }

    /**
     * 得到当前日期的所在周的周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfThisWeek(Calendar calendar) {

        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        calendar.add(Calendar.DATE, -day_of_week + 1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }

    // 获得上周星期一的日期
    public static String getPreviousMonday(Calendar calendar) {
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        calendar.add(Calendar.DATE, -day_of_week + 1 - 7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }

    // 获得下周星期一的日期
    public static String getNextMonday(Calendar calendar) {
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        calendar.add(Calendar.DATE, -day_of_week + 1 + 7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }

    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfThisWeek(Calendar calendar) {
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        calendar.add(Calendar.DATE, -day_of_week + 7);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(calendar.getTime());
    }

    /**
     * 为单数加0
     *
     * @param number 数字
     * @return 字符类型时间
     */
    public static String getNumber(int number) {

        String s = number + "";

        if (number < 10) {
            s = "0" + s;
        }

        return s;
    }

    /**
     * 设置20151212为2015-12-12
     *
     * @param number 年月日
     * @return 字符类型时间
     */
    public static String setDate(int number) {

        String date = number + "";

        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, date.length());

        date = year + "-" + month + "-" + day;

        return date;
    }
}




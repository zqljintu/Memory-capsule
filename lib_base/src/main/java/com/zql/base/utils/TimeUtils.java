package com.zql.base.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {
    public static final long TIME_SECOND = 1000;
    public static final long TIME_MINUTE = 60 * TIME_SECOND;
    public static final long TIME_HOUR = 60 * TIME_MINUTE;
    public static final long TIME_DAY = 24 * TIME_HOUR;
    public static final long TIME_HALF_AN_HOUR = 30 * TIME_MINUTE;
    public static final SimpleDateFormat UTC_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat YEAR_MONTH_DAY = new SimpleDateFormat("yyyy年MM月dd日");


    /**
     * 获取当前时区信息
     *
     * @return
     */
    public static String getCurrentTimeZone() {
        TimeZone timeZone = TimeZone.getDefault();
        return createGMTFormatString(true, true, timeZone.getRawOffset());
    }

    /**
     *上传到服务器需要的时间格式
     */
    public static String getUTCCurrentTimeZone(){
        return getTime(getCurrentTimeInLong(), UTC_DATE_FORMAT);
    }

    /**
     * UTC时间转换为正常的时间显示calender
     * @param time
     * @return
     */

    public static Calendar getDataFromUTCTimeZone(String time){
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = UTC_DATE_FORMAT.parse(time);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    /**
     *
     */

    private static String createGMTFormatString(boolean includeGmt,
                                                boolean includeMinuteSeparator, int offsetMillis) {
        int offsetMinutes = offsetMillis / 60000;
        char sign = '+';
        if (offsetMinutes < 0) {
            sign = '-';
            offsetMinutes = -offsetMinutes;
        }
        StringBuilder builder = new StringBuilder(9);
        if (includeGmt) {
            builder.append("GMT");
        }
        builder.append(sign);
        appendNumber(builder, 2, offsetMinutes / 60);
        if (includeMinuteSeparator) {
            builder.append(':');
        }
        appendNumber(builder, 2, offsetMinutes % 60);
        return builder.toString();
    }

    private static void appendNumber(StringBuilder builder, int count, int value) {
        String string = Integer.toString(value);
        for (int i = 0; i < count - string.length(); i++) {
            builder.append('0');
        }
        builder.append(string);
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * 过去多少天
     *
     * @param day 天数
     */
    public static boolean pastDay(long time, int day) {
        return Math.abs(time - System.currentTimeMillis()) >= TIME_DAY * day;
    }

    public static int pastDay(long time) {
        return (int) (time / TIME_DAY);
    }

    /**
     * 过去多少小时
     *
     * @param hour 小时数
     */
    public static boolean pastOneHour(long time, int hour) {
        return Math.abs(time - System.currentTimeMillis()) >= TIME_HOUR * hour;
    }

    /**
     * 过去多少分钟
     *
     * @param minute 分钟数
     */
    public static boolean pastOneMinute(long time, int minute) {
        return Math.abs(time - System.currentTimeMillis()) >= TIME_MINUTE * minute;
    }

    /**
     * 过去多少小时
     *
     * @param second 秒数数
     */
    public static boolean pastOneSecond(long time, int second) {
        return Math.abs(time - System.currentTimeMillis()) >= TIME_SECOND * second;
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    /**
     * @return hour
     */
    public static int getCurrentHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }


    /**
     * @return day
     */
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回起始时间与当前时间的时间差
     *
     * @param time 起始时间
     * @return
     */
    public static long getTimeIntervalToNow(long time) {
        return getCurrentTimeInLong() - time;
    }

    /**
     * 获取日期类型时间
     *
     * @param timeInMillis
     * @return
     */
    public static String getTimeInData(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_DATE);
    }

    /**
     * 是否在夜间 22点到7点
     */
    public static boolean isNight() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 22 && hour <= 7;
    }


    /**
     * @return true if clock is set to 24-hour mode
     */
    public static boolean get24HourMode(final Context context) {
        return DateFormat.is24HourFormat(context);
    }

    public static int[] convertToTime(long time) {
        int hour = (int) (time / TIME_HOUR);
        int minutes = (int) ((time % TIME_HOUR) / TIME_MINUTE);
        return new int[]{hour, minutes};
    }
}

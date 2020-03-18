package com.fyd.cygl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Date 工具类
 * Created by lychun on 2017/12/07.
 */

public class DateUtils {

    /**
     * 得到几天前的时间
     *
     * @param d  时间
     * @param day 几天
     * @return 结果
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     *
     * @param d  时间
     * @param day 几天
     * @return 结果
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 取得当前时间戳（精确到秒）
     */
    public static String getCurrTimeStamp() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }
    /**
     *  获取当前日期
     */
    public static String getDate(){
        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp);
        return date;
    }
    /**
     *  获取当前日期Date
     */
    public static Date getDate2(){
        //Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String date = sdf.format(new Date());
        System.out.println("date:"+date);
        Date dateTime = null;
        try {
            dateTime = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("sdasd"+dateTime.toString());
        return dateTime;
    }
    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Date 转化为时间戳
     * @param date 时间
     * @return
     */
    public static String dateTimeStamp(Date date) {
        return String.valueOf(date.getTime() / 1000);
    }

    /**
     * 将String转化为Date
     *
     * @param str  字符串
     * @param format 格式
     * @return 结果
     */
    public static Date stringToDate(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);//小写的mm表示的是分钟
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //将String转化为Date
    public static Date stringToDate(String str) {
        return stringToDate(str, "yyyy-MM-dd");
    }


    /**
     * 将Date转化为String
     *
     * @param date  时间
     * @param format 转化的格式
     * @return 结果
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(date);
        return result;
    }

    //将时间转化为 年-月-日 的格式
    public static String dateToString(Date date) {
        return dateToString(date, "yyyy-MM-dd");
    }
/**
 * 获取指定月的天数
 *
 * */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}

package com.xiaofengzi.wxapi.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BirthdayUtil {

    public static int getAgeByBirthday(String birthday) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date bDate = sf.parse(birthday);
        return getAgeByBirthday(bDate);
    }

    public static int getAgeByBirthday(Date birthday) throws ParseException {
        // Date date = new Date();
        // BigDecimal day = new BigDecimal((date.getTime()-birthday.getTime())/(24*60*60*1000) + 1);
        // BigDecimal year = day.divide(new BigDecimal(365), BigDecimal.ROUND_DOWN);
        // return year.intValue();
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException("出生时间大于当前时间!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;// 注意此处，如果不加1的话计算结果是错误的
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
                else {
                    // do nothing
                }
            }
            else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }
    
    public static Date getBirthdayByAge(int age) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -age);
        return cal.getTime();
    }
    
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1.0");
        a = a.setScale(0, BigDecimal.ROUND_HALF_UP);
        System.out.println(a);
        /*Date date = getBirthdayByAge(55);
        String formatDate = DateUtil.formatDate(date);
        System.out.println(formatDate);*/
    }
}

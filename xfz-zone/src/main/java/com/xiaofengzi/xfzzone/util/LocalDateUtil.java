package com.xiaofengzi.xfzzone.util;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * @author ：guojianchao
 * @description ：
 * @date ： 2019/11/12 14:43
 */
public class LocalDateUtil {


        public final static String FORMAT_DATETIME_PATTERN="yyyy-MM-dd HH:mm:ss";
        public final static String FORMAT_SJDATETIME_PATTERN="yyyyMMddHHmmss";
        public final static String FORMAT_DATE_PATTERN="yyyy-MM-dd";
        public final static String FORMAT_SJDATE_PATTERN4="yyyyMMdd";


        /**
         * 将localDate 按照一定的格式转换成String
         * @param localDate
         * @param pattern
         * @return
         */
        public static String localDateFormat(LocalDate localDate,String pattern){
            return localDate.format(DateTimeFormatter.ofPattern(pattern));
        }

        /**
         * 将localDateTime 按照一定的格式转换成String
         * @param localDateTime
         * @param pattern
         * @return
         */
        public static String localDateTimeFormat(LocalDateTime localDateTime,String pattern){
            return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        }

        /**
         * 将String 按照pattern 转换成LocalDate
         * @param time
         * @param pattern
         * @return
         */
        public static LocalDate localDateParse(String time,String pattern){
            return LocalDate.parse(time,DateTimeFormatter.ofPattern(pattern));
        }

        /**
         * 将String 按照pattern 转换成LocalDateTime
         * @param time
         * @param pattern
         * @return
         */
        public static LocalDateTime localDateTimeParse(String time,String pattern){
            return LocalDateTime.parse(time,DateTimeFormatter.ofPattern(pattern));
        }

    /**
     * 将String 按照pattern 转换成LocalTime
     * @param time
     * @param pattern
     * @return
     */
    public static LocalTime localTimeParse(String time,String pattern){
        return LocalTime.parse(time,DateTimeFormatter.ofPattern(pattern));
    }

        /**
         * 将date转换成String
         * @param date
         * @param pattern
         * @return
         */
        public static String dateFormat(Date date,String pattern){
            return localDateTimeFormat(dateToLocalDateTime(date),pattern);
        }

        /**
         * 将LocalDate 转换成 Date
         * @param localDate
         * @return
         */
        public static Date localDateToDate(LocalDate localDate){
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
            return Date.from(zdt.toInstant());
        }

        /**
         * 将LocalDateTime 转换成 Date
         * @param localDateTime
         * @return
         */
        public static Date localDateTimeToDate(LocalDateTime localDateTime){
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            return Date.from(zdt.toInstant());
        }

        /**
         * 将 Date 转换成LocalDate
         * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
         * @param date
         * @return
         */
        public static LocalDate dateToLocalDate(Date date){
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDate();
        }

        /**
         * 将 Date 转换成LocalDateTime
         * atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
         * @param date
         * @return
         */
        public static LocalDateTime dateToLocalDateTime(Date date){
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        }

        /**
         * 计算两个LocalDateTime 之间的毫秒数
         * @param time1
         * @param time2
         * @return
         */
        public static Long minusToMillsLocalDateTime(LocalDateTime time1,LocalDateTime time2){
            return Duration.between(time1, time2).toMillis();
        }

        /**
         * 计算两个LocalTime 之间的毫秒数
         * @param time1
         * @param time2
         * @return
         */
        public static Long minusToMillsLocalTime(LocalTime time1,LocalTime time2){
            return Duration.between(time1, time2).toMillis();
        }

        /**
         * 计算两个LocalDate 之间的毫秒数
         * @param time1
         * @param time2
         * @return
         */
        public static Long minusToMillsLocalDate(LocalDate time1,LocalDate time2){
            return Duration.between(time1, time2).toMillis();
        }
        /**
         * 计算两个LocalDate 之间的Period
         * @param time1
         * @param time2
         * @return
         */
        public static Period periodLocalDate(LocalDate time1,LocalDate time2){
            return Period.between(time1,time2);
        }
        /**
         * 计算两个Date 之间的Period
         * @param date1
         * @param date2
         * @return
         */
        public static Period periodDate(Date date1,Date date2){
            return periodLocalDate(dateToLocalDate(date1),dateToLocalDate(date2));
        }
        /**
         * 计算两个Date之间的 Period
         * @param time1
         * @param time2
         * @return
         */
        public static Long minusToMillsDate(Date time1,Date time2){
            return minusToMillsLocalDateTime(dateToLocalDateTime(time1),dateToLocalDateTime(time2));
        }

}

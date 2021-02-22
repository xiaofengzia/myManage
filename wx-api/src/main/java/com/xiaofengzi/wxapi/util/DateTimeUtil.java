package com.xiaofengzi.wxapi.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期格式化工具类
 */
public class DateTimeUtil {

    /**
     * 格式 yyyy年MM月dd日 HH:mm:ss
     *
     * @param dateTime
     * @return
     */
    public static String getDateTimeDisplayString(LocalDateTime dateTime) {
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = dtf2.format(dateTime);

        return strDate2;
    }

    /**
     * 生成当前时间年月日时分秒毫秒的字符串
     * @author lichao
     * @date 2019年10月30日16:06:27
     * @return String
     */
    public static String getDateString(){
        Calendar nowtime = new GregorianCalendar();
        String dateString=""+String.format("%04d", nowtime.get(Calendar.YEAR))+
                String.format("%02d", nowtime.get(Calendar.MONTH)+1)+
                String.format("%02d", nowtime.get(Calendar.DATE))+
                String.format("%02d", nowtime.get(Calendar.HOUR))+
                String.format("%02d", nowtime.get(Calendar.MINUTE))+
                String.format("%02d", nowtime.get(Calendar.SECOND))+
                String.format("%03d", nowtime.get(Calendar.MILLISECOND));
        return dateString;
    }
    
    /**
     * 获取当前时间年月日时分秒毫秒字符串
     * @author zhangxinbing
     * @date 2019.11.15
     * @return 年月日时分秒毫秒 eg:201911150925139
     */
    public static String getDate_yyyyMMddHHmmssSSS(){
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	String s = df.format(new Date());
        return s;
    }
    
    /**
     * 获取当前时间年月日
     * @author zhangxinbing
     * @date 2019.11.15
     * @return 年月日时分秒毫秒 eg:201911150925139
     */
    public static String getDate_yyyyMMdd(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String s = df.format(new Date());
        return s;
    }
    
}

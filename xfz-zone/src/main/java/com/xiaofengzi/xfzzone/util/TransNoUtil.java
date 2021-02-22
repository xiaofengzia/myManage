package com.xiaofengzi.xfzzone.util;

/**
 * 流水号生成工具类
 * @author lichao
 * @date 2019年11月14日13:59:08
 */
public class TransNoUtil {
	
    /**
     * 生成流水号
     * @param code 入参字符串
     * @return
     */
    public static String getTransNo(String code){
        String transNo="";
        if(StringUtil.isEmpty(code) || StringUtil.getLength(code)>15){
        	return null;
        }else {
        	transNo = code+DateTimeUtil.getDate_yyyyMMddHHmmssSSS()+StringUtil.generateWord();
        	return transNo;
        }
    }
    
    /**
     * 生成流水号
     * @param
     * @return
     */
    public static String getCoreNo(){
        return DateTimeUtil.getDate_yyyyMMdd()+StringUtil.coreWord();      
    }
    
    

}

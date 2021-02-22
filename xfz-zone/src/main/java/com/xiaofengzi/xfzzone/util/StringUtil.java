package com.xiaofengzi.xfzzone.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

public class StringUtil extends StringUtils {
    protected static Logger logger = LogManager.getLogger();

    /**
     * 将object 转为 string value并去空格
     * 若object为null返回空字串
     *
     * @param value
     * @return
     */
    public static String getString(Object value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value).trim();
    }
    
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断字符串是否非空（不为null与“”）
     *
     * @param str 字符串
     * @return true-非空，false-为空
     */
    public static boolean isNotEmpty(String str) {
        return str != null && str.length() != 0;
    }

    /**
     * 判断字符串是否为空（不为null或“”）
     *
     * @param str 字符串
     * @return true-为空，false-非空
     */
    public static boolean isEmpty(String str) {
        return !StringUtil.isNotEmpty(str);
    }

    /**
     * 将字符串的首字符大写
     *
     * @param str 字符串
     * @return 大写后的字符串
     */
    public static String firstToUpperCase(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 将字符串的首字符小写
     *
     * @param str 字符串
     * @return 小写后的字符串
     */
    public static String firstToLowerCase(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 将字符串进行编码
     *
     * @param str  字符串
     * @param mode 编码方式
     * @return 编码后的字符串
     */
    public static String encode(String str, String mode) {
        String target = str;
        try {
            target = URLEncoder.encode(str, mode);
        } catch (Exception e) {
            logger.error("", e);
        }
        return target;
    }

    /**
     * 将字符串进行解码
     *
     * @param str  字符串
     * @param mode 解码方式
     * @return 解码后的字符串
     */
    public static String decode(String str, String mode) {
        String target = str;
        try {
            target = URLDecoder.decode(str, mode);
        } catch (Exception e) {
            logger.error("", e);
        }
        return target;
    }

    /**
     * 将字符串进行MD5加密（不可逆）
     *
     * @param str  被加密的字符串
     * @param mode 加密方式（例如：MD5）
     * @return 已加密的字符串
     */
    public static String encrypt(String str, String mode) {
        String target = "";
        try {
            if (mode.equalsIgnoreCase("MD5")) {
                char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                byte temp[] = md.digest();
                char chars[] = new char[16 * 2];
                int k = 0;
                for (int i = 0; i < 16; i++) {
                    byte byte0 = temp[i];
                    chars[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    chars[k++] = hexDigits[byte0 & 0xf];
                }
                target = new String(chars);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return target;
    }

    /**
     * 获取数字随机码
     *
     * @param range 数字范围
     * @return 数字随机码
     */
    public static String getRandomCode(int range) {
        StringBuffer randomCode = new StringBuffer();
        try {
            Random random = new Random();
            char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
            for (int i = 0; i < range; i++) {
                randomCode.append(codeSequence[random.nextInt(codeSequence.length)]);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return randomCode.toString();
    }
    /**
     * 生成四位字母和数字的随机数
     * @return
     */
    public  static String generateWord() {
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z" };
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
        return result;
    }
    
    /**
     * 生成8位字母和数字的随机数
     * @return
     */
    public  static String coreWord() {
        String[] beforeShuffle = new String[] { "0","1", "2", "3", "4", "5", "6", "7",
                "8", "9"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(0, 8);
        return result;
    }
    
    /**
     * 生成3位随机数
     * @return
     */
    public  static String coreWordThree() {
        String[] beforeShuffle = new String[] { "0","1", "2", "3", "4", "5", "6", "7",
                "8", "9"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(0, 3);
        return result;
    }

    /**
     * 字符串替换参数方法（StringUtil.replaceParams）有两个参数，
     * 第一个是原始字符串，占位符格式为“?test?”，问号中间是参数名。
     * 第二个是参数的DTO，DTO的属性名必须和占位符中的参数名完全一致才能替换（注意大小写）。
     *
     * @param str
     * @param params
     * @return
     * @throws Exception
     */
    public static final String replaceParams(String str, Object params) throws Exception {

        System.out.println("原始字符串：" + str);
        Map<String, String> paramsMap = StringUtil.getParamsMap(params);
        Set<String> keySet = paramsMap.keySet();
        if (str.contains("?")) {
            String[] strs = str.split("\\?");
            for (String s : strs) {
                if (s != null && !"".equals(s)) {
                    if (keySet.contains(s)) {
                        str = str.replace("?" + s + "?", paramsMap.get(s));
                    }
                }
            }
        }
        System.out.println("替换后字符串：" + str);
        return str;
    }

    public static Map<String, String> getParamsMap(Object params) throws Exception {

        Map<String, String> paramsMap = new HashMap<String, String>();
        
        Class<? extends Object> clazz = params.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(params) == null) {
                paramsMap.put(field.getName(), "");
            } else {
                paramsMap.put(field.getName(), field.get(params).toString());
            }
        }
        return paramsMap;
    }

    /**
     * 补位
     *
     * @param src         源
     * @param length      补充到的长度
     * @param paddingType 1:补0  2:补空格
     * @param leftOrRight 1:左补   2:右补
     * @return
     */
    public static String padding(String src, int length, int paddingType, int leftOrRight) {
        String padddingStr = null;
        StringBuffer buffer = new StringBuffer(src);
        if (paddingType == 1) {
            padddingStr = "0";
        } else {
            padddingStr = " ";
        }

        while (buffer.length() < length) {
            if (leftOrRight == 1) {
                buffer.insert(0, padddingStr);
            } else {
                buffer.append(padddingStr);
            }
        }
        return buffer.toString();
    }

    /**
     * 字符串星号加密
     *
     * @param str    要加密的字符串
     * @param params 1：手机号 2：银行账号
     * @return
     */
    public static String asteriskByString(String str, String params) {
        if (StringUtils.isNotEmpty(str)) {
            params = params.trim();
            if (params.contains("1")) {//手机号加密
                String star = "";
                for (int i = 0; i < str.length() - 7; i++) {
                    star += "*";
                }
                str = str.substring(0, 3) + star + str.substring(str.length() - 4);
            } else if (params.contains("2")) {//银行账号加密
                String star = "";
                for (int i = 0; i < str.length() - 8; i++) {
                    star += "*";
                }
                str = str.substring(0, 4) + star + str.substring(str.length() - 4);
            } else if (params.contains("3")) {//手机号加密，中间6位
                String star = "";
                for (int i = 0; i < str.length() - 5; i++) {
                    star += "*";
                }
                str = str.substring(0, 2) + star + str.substring(str.length() - 3);
            }
        }
        return str;
    }

    /**
     * 将请求报文从post提交的body中取出
     *
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    public static String getRequestStringUtf8(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        try {
            String demo = "";
            while ((demo = br.readLine()) != null) {
                buffer.append(demo + "\n");
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }

        String retStr = buffer.toString();
        // 如果字符串最后一位是换行符则删除该换行符
//        if(null != retStr && (retStr.lastIndexOf('\n') == retStr.length()-1)){
//        	retStr = retStr.substring(0, retStr.length()-1);
//        }
//		
        return retStr;
    }
    
    /**
     * 依次截取字符串的每个字符，根据字符编码获取其字节数temp_len,byetTotal用以记录每次截取字符后及之前截取字符字节数之和,然后判断
     * byetTotal是否小于等于所需要截取字节长度
     * （length）,如果小于说明还没超过所需要截取字节长度,那么截取字符的长度（charLen）+1，如果
     * byetTotal>length说明已经超出所需要截取的字节长度，此时的charLen就是所需要截取原字符串的长度
     * 
     * @param input
     *            需要截取的字符串
     * @param length
     *            需要截取的字节数
     * @param encoding
     *            字符的编码格式
     * @return 截取后的字符串
     * @throws UnsupportedEncodingException
     */
    public static String subStringByByteGbk(String input, int length) throws UnsupportedEncodingException {

        if (null == input) {
            return null;
        }

        // 字符长度
        int characterNum = input.length();

        // 截取到当前字符时的字节数
        int byetTotal = 0;

        // 应当截取到的字符的长度
        int charLen = 0;

        /**
         * 循环传入字符串比较字节长度和字符长度
         */
        for (int i = 0; i < characterNum; i++) {
            String temp = input.substring(i, i + 1);
            int temp_len = temp.getBytes("GBK").length;
            byetTotal += temp_len;
            if (byetTotal <= length) {
                charLen++;
            } else {
                break;
            }
        }
        return input.substring(0, charLen);
    }
    
    public static String[] splitByParam(String str, String param) {
        if(isEmpty(str) || isEmpty(param)) {
            return null;
        } 
        return str.split(param);
        
    }
    
    /**
     * 计算字符串长度
     * @param s
     * @return
     */
    public static int getLength(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

}

package com.xiaofengzi.wxapi.util;

import org.apache.axis.encoding.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

public class DESUtil {

    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

    public static final String KEY = "12345678";
    
    public static final String BHKEY = "djnk2645";
    
    public static final String LOGICKEY ="login321";

    /**
     * DES算法，加密
     * 
     * @param data 待加密字符串
     * @param key 加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws Exception 异常
     */
    public static String encode(String key, String data) throws Exception {
        return encode(key, data.getBytes("gbk"));
    }

    public static String encode(String data) throws Exception {
        return encode(KEY, data.getBytes("gbk"));
    }

    /**
     * DES算法，加密
     * 
     * @param data 待加密字符串
     * @param key 加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws Exception 异常
     */
    public static String encode(String key, byte[] data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);

            byte[] bytes = cipher.doFinal(data);
            return Base64.encode(bytes);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }
    /**
     * DES算法，解密
     * 
     * @param data 待解密字符串
     * @param key 解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    public static byte[] GreatLifeDecode(String key, byte[] data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
            return cipher.doFinal(data);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * DES算法，解密
     * 
     * @param data 待解密字符串
     * @param key 解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     * @throws Exception 异常
     */
    public static byte[] decode(String key, byte[] data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
            return cipher.doFinal(data);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 获取编码后的值
     * 
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static String decodeValue(String key, String data) {
        byte[] datas;
        String value = null;
        try {
            if (System.getProperty("os.name") != null
                    && (System.getProperty("os.name").equalsIgnoreCase("sunos") || System.getProperty("os.name")
                            .equalsIgnoreCase("linux"))) {
                datas = decode(key, Base64.decode(data));
            }
            else {
                datas = decode(key, Base64.decode(data));
            }

            value = new String(datas, "gbk");
        }
        catch (Exception e) {
            e.printStackTrace();
            value = "";
        }
        return value;
    }

    public static String decode(String data) {
        return decodeValue(KEY, data);
    }
    public static String bhLifeDecode(String key,String data) {
        return bhLifePolicyDecodeValue(key, data);
    }
    
    public static String bhLifeDecode(String data) {
        return bhLifePolicyDecodeValue(BHKEY, data);
    }
    
    public static String bhLifePolicyEncoder(String key,String data) throws Exception  {
    	 try {
             DESKeySpec dks = new DESKeySpec(key.getBytes());

             SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
             // key的长度不能够小于8位字节
             Key secretKey = keyFactory.generateSecret(dks);
             Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
             IvParameterSpec iv = new IvParameterSpec(key.getBytes());
             AlgorithmParameterSpec paramSpec = iv;
             cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);

             byte[] bytes = cipher.doFinal(data.getBytes("gbk"));
             return Base64.encode(bytes);
         }
         catch (Exception e) {
             throw new Exception(e);
         }
	}
    
    public static String bhLifePolicyEncoder(String data) throws Exception  {
        try {
            DESKeySpec dks = new DESKeySpec(BHKEY.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(BHKEY.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);

            byte[] bytes = cipher.doFinal(data.getBytes("gbk"));
            return Base64.encode(bytes);
        }
        catch (Exception e) {
            throw new Exception(e);
        }
   }
    /**
     * 获取编码后的值
     * 
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static String bhLifePolicyDecodeValue(String key, String data) {
    	 byte[] datas;
         String value = null;
         try {
             if (System.getProperty("os.name") != null
                     && (System.getProperty("os.name").equalsIgnoreCase("sunos") || System.getProperty("os.name")
                             .equalsIgnoreCase("linux"))) {
                 datas = GreatLifeDecode(key, Base64.decode(data));
             }
             else {
                 datas = GreatLifeDecode(key, Base64.decode(data));
             }

             value = new String(datas, "gbk");
         }
         catch (Exception e) {
             e.printStackTrace();
             value = "";
         }
         return value;
    }
    
    /**
     * 转换byte数组为16进制字符串, 逆操作方法是hexStrToByte
     * @param keyByte
     * @return
     */
    public static String byteToHexStr(byte[] keyByte)
	{
		StringBuilder hexByteStr = new StringBuilder("");
		for (int i = 0; i < keyByte.length; i++) {
			String rHexStr = Integer.toHexString(keyByte[i] + 128);
			if (rHexStr.length() < 2) {
				rHexStr = "0" + rHexStr;
			}
			hexByteStr.append(rHexStr);
		}
		return hexByteStr.toString();
	}

    /**
     * 转换16进制字符串为byte数组,逆操作方法是byteToHexStr
     * @param keyByteStr
     * @return
     */
	public static byte[] hexStrToByte(String keyByteStr) {
		int byteLen = keyByteStr.length() / 2;
		byte[] rByteA = new byte[byteLen];
		for (int i = 0; i < keyByteStr.length(); i += 2) {
			rByteA[i / 2] = Byte
					.parseByte(""
							+ (Integer.parseInt(keyByteStr.substring(i, i + 2),
									16) - 128));
		}
		return rByteA;
	}

}

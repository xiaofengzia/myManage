package com.xiaofengzi.wxapi.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * JSON转换工具类
 */
public class GsonUtil {
	
	
	public static Gson gson;
	
	static{
		gson = new GsonBuilder()
				.enableComplexMapKeySerialization()
				.disableHtmlEscaping()
				.setDateFormat("yyyy-MM-dd")
				.create();
	
	}
	/**
	 * 将object转为json字符串
	 * @param pObj
	 * @return
	 */
	public static String writeValue(Object pObj){
		return gson.toJson(pObj);
	}

	/**
	 * 将content转为java object
	 * @param pContent
	 * @param obj
	 * @param <T>
	 * @return
	 */
	public static <T> Object readValue(String pContent,Object obj){
		return gson.fromJson(pContent, (Class<T>) obj);
	}

	 /**
     * map转object
     * @description
     * @version 1.0.0
     * @author xwy
     * @date 2018年10月19日 下午5:17:43
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static  Object mapToObject(Map<String, String> map, Class<?> beanClass) throws Exception {    
         if (map == null)  
             return null;    
   
         Object obj = beanClass.newInstance();  
   
         Field[] fields = obj.getClass().getDeclaredFields();   
         for (Field field : fields) {    
             int mod = field.getModifiers();    
             if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                 continue;    
             }    
   
             field.setAccessible(true);    
             field.set(obj, map.get(field.getName()));   
         }   
   
         return obj;    
   }

	/**
	 * json转DTO
	 * @description
	 * @version 1.0.0
	 * @author xwy
	 * @date 2018年10月18日 下午3:21:39
	 * @param pContent 传入json串
	 * @param obj 结果DTO
	 * @param params value节点所在所有父节点的名称
	 * @return obj
	 * @throws JSONException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> Object changeLevelValue(String pContent,Object obj, String... params) throws JSONException{
		JSONObject josnObject = new JSONObject(pContent);
		if(params.length != 0) { // 传空，则直接转换
			for (String param : params) {
				josnObject = josnObject.getJSONObject(param);
			}
			if(josnObject == null) {
				return null;
			}
		}

		return new Gson().fromJson(josnObject.toString(), (Class<T>) obj);
	}
	
	/**
	 * jsonObject属性值处理
	 */
	public static JsonObject dealJson(String pContent) {
	    JsonParser jsonParser=new JsonParser();
	    if(StringUtil.isEmpty(pContent)) {
	        return null;  
	    }
        return jsonParser.parse(pContent).getAsJsonObject();
	}
	
	
	/**
     * jsonObject属性值处理
     */
    public static boolean isNotEmpty(JsonObject jsonObject, String p1) {
        if(jsonObject.get(p1) != null) {
            return true; 
        }
        return false;
    }
}

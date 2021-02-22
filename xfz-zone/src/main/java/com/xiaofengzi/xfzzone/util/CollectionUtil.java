package com.xiaofengzi.xfzzone.util;


import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 
 * @author lumj_focoon
 *
 */
public class CollectionUtil
{

	/**
	 * 判断集合是否非空
	 *
	 * @param collection 集合（包括：List、Set）
	 * @return true-非空，false-为空
	 */
	public static <T> boolean isNotEmpty(Collection<T> collection)
	{

		return collection != null && collection.size() != 0;
	}

	/**
	 * 判断集合是否为空
	 *
	 * @param collection 集合（包括：List、Set）
	 * @return true-为空，false-非空
	 */
	public static <T> boolean isEmpty(Collection<T> collection)
	{
		return !CollectionUtil.isNotEmpty(collection);
	}

	/**
	 * 判断映射是否非空
	 *
	 * @param map 映射
	 * @return true-非空，false-为空
	 */
	public static <K, V> boolean isNotEmpty(Map<K, V> map)
	{
		return map != null && !map.isEmpty();
	}

	/**
	 * 判断映射是否为空
	 *
	 * @param map 映射
	 * @return true-为空，false-非空
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map)
	{
		return !CollectionUtil.isNotEmpty(map);
	}

	/**
	 * 将映射转化为列表
	 *
	 * @param map 映射
	 * @return 列表
	 */
	public static <K, T> List<T> transferList(Map<K, T> map)
	{
		List<T> list = new ArrayList<T>();
		if (map != null)
		{
			list.addAll(map.values());
		}
		return list;
	}
	
	/**
	 * 将map中value为null或空字符的去掉
	 * @param map
	 * @return
	 */
	public static  Map<String, Object> removeNullValue(Map<String, Object> map){
		
		    Map<String, Object>  newMap=new HashMap<String, Object>();
		    Iterator it=map.entrySet().iterator();
	    	while(it.hasNext()){
	              Map.Entry entry=(Map.Entry)it.next();
	              if(entry.getValue() instanceof String){
	            	  String value=((String)entry.getValue()).trim();
	            	  if(StringUtils.isEmpty(value)||value=="null"){
	            		   continue;
	            	  }
	              }else if("".equals(entry.getValue())||entry.getValue()==null){
	            	      continue;
	              }
  	              newMap.put((String)entry.getKey(),entry.getValue()); 
  	        }
	    	Set<Map.Entry<String, Object>> set=newMap.entrySet();
	        for (Map.Entry<String, Object> entry : set) {
			      System.out.println(entry.getKey()+"======="+entry.getValue()); 
		    }
	        return newMap;	
	}
	
	/**判断集合是否相等*/
	public static boolean isEquals(List<String> list1,List<String> list2){
        if(null != list1 && null != list2){
            if(list1.containsAll(list2) && list2.containsAll(list1)){
                return true;
            }
            return false;
        }
        return true;
    }
}
package com.xiaofengzi.wxapi.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * OrderDTO、InsuranceInputDTO的工具类
 * 
 * @author gu 140113
 * @version 1.0
 */
public class DTOUtil {
    /**
     * 获取对象涉及的所有dto的clazz
     * 
     * @param clazz
     * @return
     */
    public static Class<?>[] getInvolvedClazz(Class<?> clazz)
    {
        return getInvClazz(clazz).toArray(new Class<?>[]{}); 
    }
    /**
     * 获取类型涉及的所有dto的clazz
     * 
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Set<Class<?>> getInvClazz(Class<?> clazz)
    {
        Set<Class<?>> clazzSet = new HashSet<Class<?>>();
        if(clazz.getName().toLowerCase().contains("generated"))
        {
            clazzSet.add(clazz);
        }
        else if(clazz.getName().toLowerCase().startsWith("java"))
        {
            return Collections.EMPTY_SET;
        }
        else 
        {
            clazzSet.add(clazz);
            if(clazz.getSuperclass()!=null)
            {
                if(!clazz.getSuperclass().getName().toLowerCase().contains("generated"))
                {
                    clazzSet.addAll(getInvClazz(clazz.getSuperclass()));
                }
            }
            for(Field field:clazz.getDeclaredFields())
            {
                if(Collection.class.isAssignableFrom(field.getType()))
                {
                     clazzSet.add(field.getType());
                     ParameterizedType pt = (ParameterizedType) field.getGenericType();
                     for(Type inType:pt.getActualTypeArguments())
                     {
                         clazzSet.addAll(getInvClazz((Class<?>)inType));
                     }
                }
                else if(field.getType().getName().startsWith("java"))
                {
                    continue;
                }
                else
                {
                    clazzSet.addAll(getInvClazz(field.getType()));
                }
            }
        }
        
        return clazzSet;
    }
   
//    /**
//     * 取订单上指定路径、险种的字段
//     * 
//     * @param orderDTO 订单数据
//     * @param fieldPath 字段的路径
//     * @param riskCode 险种代码
//     * @return 符合条件的字段
//     */
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//    public static List<?> getModelFieldList(OrderDTO orderDTO, String fieldPath,String riskCode) {
//        if (StringUtils.isBlank(fieldPath)) {
//            return Collections.emptyList();
//        }
//        if (fieldPath.equalsIgnoreCase("orderDTO")) {
//            return Collections.singletonList(orderDTO);
//        }
//        String[] param = StringUtils.split(fieldPath, ".");
//        Object lastObj = orderDTO;
//        for (int i = 0; i < param.length; i++) {
//            try {
//                if (lastObj instanceof Collection) {// 暂时只处理容器,不考虑使用array的情况
//                    List<Object> tempLastList = new ArrayList<Object>();
//                    oneInColl:  for (Object oneInColl : (Collection<?>) lastObj) {
//                        
//                        //如果是险种，则险种代码必须与指定的险种代码一致，否则不处理
//                        if(oneInColl instanceof EbizOrderInsurance)
//                        {
//                            if(!riskCode.equals(((EbizOrderInsurance)oneInColl).getRiskCode()))
//                            {
//                                continue oneInColl;
//                            }
//                        }
//                        
//                        Object innerObj = ReflectionUtil.invokeGetterMethod(
//                                oneInColl, param[i]);
//                        if (innerObj instanceof Collection) {
//                            tempLastList.addAll((Collection) innerObj);
//                        } else {
//                            tempLastList.add(innerObj);
//                        }
//                    }
//                    lastObj = tempLastList;
//                } else {
//                    lastObj = ReflectionUtil.invokeGetterMethod(lastObj,
//                            param[i]);
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//        if(lastObj instanceof List)
//        {
//            return (List)lastObj;
//        }
//        else
//        {
//            return Collections.singletonList(lastObj);
//        }
//    }

}

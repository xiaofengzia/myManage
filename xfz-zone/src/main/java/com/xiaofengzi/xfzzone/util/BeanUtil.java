package com.xiaofengzi.xfzzone.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;

public class BeanUtil {

    public static void copyBeanNoCover (Object src, Object tgt) throws Exception {
        PropertyDescriptor[] tgtPDs = BeanUtils.getPropertyDescriptors(tgt.getClass());
        String[] ignores = new String[tgtPDs.length];
        int cnt = 0;
        for (PropertyDescriptor descriptor : tgtPDs) {
            String name = descriptor.getName();
            Object value = descriptor.getReadMethod().invoke(tgt);
            if (StringUtils.hasText(ObjectUtils.getDisplayString(value))) {
                ignores[cnt] = name;
                cnt++;
            }
        }
        BeanUtils.copyProperties(src, tgt, ignores);
    }
    
    public static void copyBeanNoCover (Object src, Object tgt, String[] ignore) throws Exception {
        PropertyDescriptor[] tgtPDs = BeanUtils.getPropertyDescriptors(tgt.getClass());
        String[] ignores = new String[tgtPDs.length];
        int cnt = 0;
        for (PropertyDescriptor descriptor : tgtPDs) {
            String name = descriptor.getName();
            Object value = descriptor.getReadMethod().invoke(tgt);
            if (StringUtils.hasText(ObjectUtils.getDisplayString(value))) {
                ignores[cnt] = name;
                cnt++;
            }
        }
        for (String ig : ignore) {
            ignores[cnt] = ig;
            cnt++;
        }
        BeanUtils.copyProperties(src, tgt, ignores);
    }
    
    public static void copyBeanNoNullCover (Object src, Object tgt) throws Exception {
        PropertyDescriptor[] srcPDs = BeanUtils.getPropertyDescriptors(src.getClass());
        String[] ignores = new String[srcPDs.length];
        int cnt = 0;
        for (PropertyDescriptor descriptor : srcPDs) {
            String name = descriptor.getName();
            Object value = descriptor.getReadMethod().invoke(src);
            if (value == null) {
                ignores[cnt] = name;
                cnt++;
            }
        }
        BeanUtils.copyProperties(src, tgt, ignores);
    }
    
    public static void copyBeanNoNullCover (Object src, Object tgt, String[] ignore) throws Exception {
        PropertyDescriptor[] srcPDs = BeanUtils.getPropertyDescriptors(src.getClass());
        String[] ignores = new String[srcPDs.length];
        int cnt = 0;
        for (PropertyDescriptor descriptor : srcPDs) {
            String name = descriptor.getName();
            Object value = descriptor.getReadMethod().invoke(src);
            if (value == null) {
                ignores[cnt] = name;
                cnt++;
            }
        }
        for (String ig : ignore) {
            ignores[cnt] = ig;
            cnt++;
        }
        BeanUtils.copyProperties(src, tgt, ignores);
    }

}

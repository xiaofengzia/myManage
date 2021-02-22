package com.xiaofengzi.xfzzone.controller;

import javax.servlet.http.HttpServletRequest;

import com.xiaofengzi.xfzzone.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 基础Controller
 *
 * @Description TODO
 * @Author DingZhou
 * @Date 2019-06-05 06:52
 * @Version 1.0
 **/
public class BaseController {

    /**
     * 定义日志
     */
    protected final Logger logger = LogManager.getLogger(getClass());

    /**
     * 解密JWT
     * @param request
     * @param c
     * @param <T>
     * @return
     */
    public <T> T decode(HttpServletRequest request, Class<T> c ){
        String object = request.getParameter("object");
        if(StringUtil.isEmpty(object)) {
            return null;
        }
        object = object.replaceAll(" ","+");
        T t = JSONObject.parseObject(new String(Base64Utils.decodeFromString(object)), c);
        return t;
    }

}

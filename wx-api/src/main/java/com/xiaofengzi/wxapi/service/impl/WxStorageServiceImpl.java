package com.xiaofengzi.wxapi.service.impl;

import com.xiaofengzi.wxapi.db.bo.interfaces.WxStorageBO;
import com.xiaofengzi.wxapi.db.domain.OssObject;
import com.xiaofengzi.wxapi.service.interfaces.WxStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author GuoJianChao
 * @Date 2020/3/2619:35
 * @Version 1.0
 **/
@Service
public class WxStorageServiceImpl implements WxStorageService {
    @Autowired
    private WxStorageBO wxStorageBO;

    @Override
    public OssObject findByKey(String key) {
        OssObject ossObject = new OssObject();
        ossObject.setName(key);
        return wxStorageBO.findByExample(ossObject);
    }
}

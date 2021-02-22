package com.xiaofengzi.wxapi.db.bo.interfaces;


import com.xiaofengzi.wxapi.db.domain.OssObject;

/**
 * @Description OSS系统BO
 * @Author GuoJianChao
 * @Date 2020/3/2714:22
 * @Version 1.0
 **/
public interface WxStorageBO {

    public OssObject findByExample(OssObject ossObject);

}

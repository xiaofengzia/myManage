package com.xiaofengzi.xfzzone.service.interfaces;


import com.xiaofengzi.xfzzone.db.domain.OssObject;

/**
 * @Description OSS系统
 * @Author GuoJianChao
 * @Date 2020/3/2619:33
 * @Version 1.0
 **/
public interface WxStorageService {
    /**
     * 根据文件名获取文件路径
     * @param key
     * @return
     */
    public OssObject findByKey(String key);
}

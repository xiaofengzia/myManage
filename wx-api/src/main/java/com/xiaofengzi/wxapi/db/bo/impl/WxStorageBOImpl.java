package com.xiaofengzi.wxapi.db.bo.impl;

import com.xiaofengzi.wxapi.db.bo.interfaces.WxStorageBO;
import com.xiaofengzi.wxapi.db.dao.generated.OssObjectMapper;
import com.xiaofengzi.wxapi.db.domain.OssObject;
import com.xiaofengzi.wxapi.db.domain.OssObjectExample;
import com.xiaofengzi.wxapi.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Author GuoJianChao
 * @Date 2020/3/2714:22
 * @Version 1.0
 **/
@Component
public class WxStorageBOImpl implements WxStorageBO {

    @Resource
    private OssObjectMapper ossObjectMapper;

    public OssObject findByExample(OssObject ossObject){
        OssObjectExample example = new OssObjectExample();
        OssObjectExample.Criteria criteria = example.createCriteria();
        if(ossObject.getId() != null){
            criteria.andIdEqualTo(ossObject.getId());
        }

        if(StringUtil.isNotEmpty(ossObject.getName())){
            criteria.andNameEqualTo(ossObject.getName());
        }

        List<OssObject> ossObjects = ossObjectMapper.selectByExample(example);

        if (!CollectionUtils.isEmpty(ossObjects)){
            return  ossObjects.get(0);
        }
        return null;
    }
}

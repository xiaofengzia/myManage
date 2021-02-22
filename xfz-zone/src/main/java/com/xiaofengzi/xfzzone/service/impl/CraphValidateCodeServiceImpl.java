package com.xiaofengzi.xfzzone.service.impl;

import com.xiaofengzi.xfzzone.dto.common.ConstantForWechat;
import com.xiaofengzi.xfzzone.dto.common.ValidateCode;
import com.xiaofengzi.xfzzone.service.interfaces.CraphValidateCodeService;
import com.xiaofengzi.xfzzone.util.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @name: CraphValidateCodeServiceImpl
 * @description: 图形验证码实现类
 * @author: D.Z
 * @created: 2018-10-08 07:13
 **/
@Service
public class CraphValidateCodeServiceImpl implements CraphValidateCodeService {

    @Resource
    private RedisService redisService;

    @Override
    public ValidateCode generated(String token) {

        ValidateCode vCode = new ValidateCode(120,40,4,50);
        redisService.hset(token+".wechat","graphValidateCode",vCode.getCode(), ConstantForWechat.REDIS_VALIDATEIN);

        return vCode;
    }

    @Override
    public boolean check(String code, String token) {

        String str = (String) redisService.hget(token+".wechat","graphValidateCode");
        if(code.equals(str)){
            return true;
        }

        return false;
    }
}

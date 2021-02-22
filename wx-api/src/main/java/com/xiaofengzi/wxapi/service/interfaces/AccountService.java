package com.xiaofengzi.wxapi.service.interfaces;

import com.xiaofengzi.wxapi.dto.common.TransResult;
import com.xiaofengzi.wxapi.dto.login.LoginReqDTO;

public interface AccountService {

    public TransResult<Object> accountLoigin(LoginReqDTO loginReqDTO);
}

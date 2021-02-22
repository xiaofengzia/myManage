package com.xiaofengzi.xfzzone.service.interfaces;

import com.xiaofengzi.xfzzone.dto.common.TransResult;
import com.xiaofengzi.xfzzone.dto.login.LoginReqDTO;

public interface AccountService {

    public TransResult<Object> accountLoigin(LoginReqDTO loginReqDTO);
}

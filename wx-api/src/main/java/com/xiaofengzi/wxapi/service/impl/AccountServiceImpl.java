package com.xiaofengzi.wxapi.service.impl;

import com.xiaofengzi.wxapi.db.bo.interfaces.AccountUserBO;
import com.xiaofengzi.wxapi.db.domain.AccountUser;
import com.xiaofengzi.wxapi.dto.common.TransResult;
import com.xiaofengzi.wxapi.dto.login.LoginReqDTO;
import com.xiaofengzi.wxapi.dto.login.LoginResDTO;
import com.xiaofengzi.wxapi.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountUserBO accountUserBO;

    @Override
    public TransResult<Object> accountLoigin(LoginReqDTO loginReqDTO) {
        TransResult transResult = new TransResult();
        LoginResDTO loginResDTO = new LoginResDTO();

        List<AccountUser> list = accountUserBO.getAccountUsers(loginReqDTO.getAccountCode(),loginReqDTO.getPassword());
        if(CollectionUtils.isEmpty(list)){
            transResult.failure();
        }else{
            AccountUser accountUser = list.get(0);
            loginResDTO.setAccountCode(accountUser.getUserAccount());
            transResult.setObject(loginResDTO);
            transResult.success();
        }
        return transResult;
    }
}

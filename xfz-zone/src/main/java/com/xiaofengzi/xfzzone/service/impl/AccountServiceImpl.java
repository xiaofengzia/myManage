package com.xiaofengzi.xfzzone.service.impl;

import com.xiaofengzi.xfzzone.db.bo.interfaces.AccountUserBO;
import com.xiaofengzi.xfzzone.db.domain.AccountUser;
import com.xiaofengzi.xfzzone.dto.common.TransResult;
import com.xiaofengzi.xfzzone.dto.login.LoginReqDTO;
import com.xiaofengzi.xfzzone.dto.login.LoginResDTO;
import com.xiaofengzi.xfzzone.service.interfaces.AccountService;
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

package com.xiaofengzi.wxapi.db.bo.impl;

import com.xiaofengzi.wxapi.db.bo.interfaces.AccountUserBO;
import com.xiaofengzi.wxapi.db.dao.generated.AccountUserMapper;
import com.xiaofengzi.wxapi.db.domain.AccountUser;
import com.xiaofengzi.wxapi.db.domain.AccountUserExample;
import com.xiaofengzi.wxapi.dto.login.LoginReqDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AccountUserBoImpl implements AccountUserBO {

    @Resource
    private AccountUserMapper accountUserMapper;

    @Override
    public List<AccountUser> getAccountUsers(String accountCode,String password) {
        AccountUserExample accountUserExample = new AccountUserExample();
        accountUserExample.createCriteria()
                .andUserAccountEqualTo(accountCode).
                andPasswordEqualTo(password)
                .andIsDeleteEqualTo(0);
        return accountUserMapper.selectByExample(accountUserExample);
    }
}

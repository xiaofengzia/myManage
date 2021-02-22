package com.xiaofengzi.xfzzone.db.bo.impl;

import com.xiaofengzi.xfzzone.db.bo.interfaces.AccountUserBO;
import com.xiaofengzi.xfzzone.db.dao.generated.AccountUserMapper;
import com.xiaofengzi.xfzzone.db.domain.AccountUser;
import com.xiaofengzi.xfzzone.db.domain.AccountUserExample;
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

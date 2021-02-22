package com.xiaofengzi.wxapi.db.bo.interfaces;

import com.xiaofengzi.wxapi.db.domain.AccountUser;
import com.xiaofengzi.wxapi.dto.login.LoginReqDTO;

import java.util.List;

public interface AccountUserBO {

    public List<AccountUser> getAccountUsers(String accountCode,String password);
}

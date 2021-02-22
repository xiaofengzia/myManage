package com.xiaofengzi.xfzzone.db.bo.interfaces;

import com.xiaofengzi.xfzzone.db.domain.AccountUser;

import java.util.List;

public interface AccountUserBO {

    public List<AccountUser> getAccountUsers(String accountCode,String password);
}

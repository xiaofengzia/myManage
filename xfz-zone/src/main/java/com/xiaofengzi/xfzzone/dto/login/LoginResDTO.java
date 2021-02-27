package com.xiaofengzi.xfzzone.dto.login;

import com.xiaofengzi.xfzzone.db.domain.AccountUser;

public class LoginResDTO extends AccountUser {

    /**
     * 账号
     */
    private String accountCode;

    /**
     * 验证码
     */
    private String captcha;
    /**
     * 登陆IP
     */
    private String ip;
    /**
     * 加密账号
     */
    private String accountCodeSM;

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAccountCodeSM() {
        return accountCodeSM;
    }

    public void setAccountCodeSM(String accountCodeSM) {
        this.accountCodeSM = accountCodeSM;
    }
}

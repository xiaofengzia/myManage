package com.xiaofengzi.wxapi.dto.login;

public class LoginResDTO {

    /**
     * 账号
     */
    private String accountCode;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 密码
     */
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

package com.xiaofengzi.wxapi.dto.enums;

/**
 * 登陆错误信息枚举
 */
public enum ENUM_LOGIN_MESSAGE {

    //1为成功,0为未注册,2为密码错误,3代理人已离职,4为落库发生失败,-1为发生异常
    AGENTINFONULL("0","代理人未注册"),
    SUCCESS("1", "登陆成功"),
    FAIL("2", "密码错误"),
    LEAVED("3", "代理人已离职"),
    DROP_DATEBASE("4","代理人登陆落库失败"),
    REVIEWEDSTATEFAIL("5","审批状态不正确"),
    CAPTCHAERROR("6","图形验证码错误"),
    DUMMYAGENTINFONULL("7","虚拟代理人工号不存在"),
    LOGINED("8","该工号已在其他微信上登陆"),
    UNKNOWAREA("9","暂未开放该地区"),
    UNKNOWAUTH("10","暂未开放该权限"),
    UNKNOWEMPNO("11","无效的工号"),
    UNFINDMOBLE("12","该工号无留存的手机号码"),
    ERRORMOBILECODE("13","验证码不正确"),
    EXCEPTION("-1","异常"),
    YXHBLOGIN("13","意向伙伴登录");

    private String value;
    private String name;

    private ENUM_LOGIN_MESSAGE(String value, String name) {
        this.value = value;
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}

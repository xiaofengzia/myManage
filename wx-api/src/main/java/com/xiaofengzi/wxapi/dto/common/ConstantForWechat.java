package com.xiaofengzi.wxapi.dto.common;

/**
 * 微信渠道常量配置
 */
public class ConstantForWechat{

    /**
     * 微信渠道访问根目录
     */
    public static final  String BASE_PATH = "/api/wechat";

    /**
     * request中存放AgentCode  --- 工号-虚拟代理人工号
     */
    public static final String AGENTCODE = "AgentCode";

    /**
     * 过期时间- 5天
     */
    public static final long JWT_EXPIRATIONTIME = 432_000_000;
    /**
     * JWT密码
     */
    public static final String JWT_SECRET = "oLfvbt9VxCOad4ifrLjn6XKzLE3Y";
    /**
     * Token前缀
     */
    public static final String JWT_TOKEN_PREFIX = "GREATLIFE";
    /**
     * 存放Token的Header Key
     */
    public static final String JWT_HEADER_STRING = "Authorization";

    /**
     * 验证码过期时间
     */
    public static final int REDIS_VALIDATEIN  = 300;

    /**
     * 渠道
     */
    public static final String SALE_CHANNEL  = "SaleChannel";


    /**
     * 页面权限
     */
    public static final String PAGE_INFO  = "PageInfo";

    /**
     * 账号
     */
    public static final String OPERATOR = "Operator";

    /**
     * 过期时间- 1天   86_400_000
     */
    public static final long JWT_EXPIRATIONTIME_OPERATOR = 86_400_000;

    /**
     * 公司编码
     */
    public static final String COMPANY_CODE  = "CompanyCode";

    /**
     * 公司名称
     */
    public static final String COMPANY_NAME  = "CompanyName";

    /**
     * 开放功能
     */
    public static final String FUNCTION_SET  = "FunctionSet";

    /**
     * 开放功能
     */
    public static final String EBIZ_JWT_USER  = "EbizJwtUser";


    /**
     * 工号
     */
    public static final String ACCOUNT_CODE  = "accountCode";

    /**
     * request中存放AgentCode  --- 工号-虚拟代理人工号
     */
    public static final String REALAGENTCODE = "RealAgentCode";

    public static final String BRANCHCODE = "BranchCode";

    public static final String JUMP = "Jump";


    /**
     * 过期时间- 31天
     */
    public static final long JWT_EXPIRATIONTIME_31 = 2_678_400_000L;



}

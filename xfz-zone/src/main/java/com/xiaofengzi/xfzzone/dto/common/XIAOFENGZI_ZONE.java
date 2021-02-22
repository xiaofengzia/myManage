package com.xiaofengzi.xfzzone.dto.common;

public class XIAOFENGZI_ZONE {

    public static final  String BASE_PATH = "/api/xfz";

    /**
     * 过期时间- 5天
     */
    public static final long JWT_EXPIRATIONTIME = 432_000_000;
    /**
     * JWT密码
     */
    public static final String JWT_SECRET = "xiaofengzi0810xiaokengkeng0416";
    /**
     * Token前缀
     */
    public static final String JWT_TOKEN_PREFIX = "XIAOFENGZI";
    /**
     * 存放Token的Header Key
     */
    public static final String JWT_HEADER_STRING = "Authorization";

    /**
     * 验证码过期时间
     */
    public static final int REDIS_VALIDATEIN  = 300;
    /**
     * 过期时间- 1天   86_400_000
     */
    public static final long JWT_EXPIRATIONTIME_OPERATOR = 86_400_000;

    /**
     * 账号
     */
    public static final String ACCOUNT_CODE  = "accountCode";

    public static final String JUMP = "Jump";

    /**
     * 过期时间- 31天
     */
    public static final long JWT_EXPIRATIONTIME_31 = 2_678_400_000L;
}

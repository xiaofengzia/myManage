package com.xiaofengzi.xfzzone.service.interfaces;


import com.xiaofengzi.xfzzone.dto.common.ValidateCode;

/**
 * 图形验证码
 */
public interface CraphValidateCodeService {

    /**
     * 生成图形验证码
     * @return
     */
    public ValidateCode generated(String token);

    /**
     * 验证图形验证码
     * @param code
     * @param token
     * @return
     */
    public boolean check(String code, String token);
}

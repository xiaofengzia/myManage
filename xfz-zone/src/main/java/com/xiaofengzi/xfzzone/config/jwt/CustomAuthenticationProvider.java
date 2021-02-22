package com.xiaofengzi.xfzzone.config.jwt;

import com.xiaofengzi.xfzzone.dto.common.TransResult;
import com.xiaofengzi.xfzzone.dto.enums.ENUM_LOGIN_MESSAGE;
import com.xiaofengzi.xfzzone.dto.login.LoginReqDTO;
import com.xiaofengzi.xfzzone.service.interfaces.AccountService;
import com.xiaofengzi.xfzzone.service.interfaces.CraphValidateCodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @name: CustomAuthenticationProvider
 * @description: 自定义身份认证验证组件
 * @author: D.Z
 * @created: 2018-08-22 20:22
 **/
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private AccountService accountService;

    @Resource
    private CraphValidateCodeService craphValidateCodeService;

    protected static Logger logger = LogManager.getLogger();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TransResult<Object> transResult = new TransResult<Object>();

        // 获取认证的工号 & 密码
        String accountCode = authentication.getName();
        LoginReqDTO loginReqDTO = (LoginReqDTO) authentication.getCredentials();
        
        // 短信登录
            if(!craphValidateCodeService.check(loginReqDTO.getCaptcha(), loginReqDTO.getRandomToken())){
                logger.info("[代理人登陆][结束][图形验证码验证失败]");
                throw new BadCredentialsException(ENUM_LOGIN_MESSAGE.CAPTCHAERROR.getValue());
            }
        try {

            loginReqDTO.setAccountCode(accountCode);
            if(StringUtils.isEmpty(loginReqDTO.getAccountCode())){
                logger.info("[代理人登陆][结束][代理人工号不能为空]");
                throw new BadCredentialsException("-1");
            }

            transResult = accountService.accountLoigin(loginReqDTO);

            transResult.setResultInfo("");
            // 1为成功,0为未注册,2为密码错误,3代理人已离职,4为登陆发生失败,-1为发生异常       
            if (ENUM_LOGIN_MESSAGE.SUCCESS.getValue().equals(transResult.getResultCode()) ) {
                Authentication auth = new UsernamePasswordAuthenticationToken(transResult, null, null);
                return auth;
            } else {
                throw new BadCredentialsException(transResult.getResultCode());
            } 
        } catch (Exception e) {

            if (ENUM_LOGIN_MESSAGE.EXCEPTION.getValue().equals(transResult.getResultCode())) {
                e.printStackTrace();
                logger.info("[密码验证出现异常:" + e.getMessage() + "]");
                throw new BadCredentialsException("-1");
            } else {
                throw e;
            }
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

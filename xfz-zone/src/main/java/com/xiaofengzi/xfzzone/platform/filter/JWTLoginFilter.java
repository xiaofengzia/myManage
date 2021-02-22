package com.xiaofengzi.xfzzone.platform.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaofengzi.xfzzone.config.jwt.TokenAuthenticationService;
import com.xiaofengzi.xfzzone.dto.common.TransResult;
import com.xiaofengzi.xfzzone.dto.login.LoginReqDTO;
import com.xiaofengzi.xfzzone.util.GsonUtil;
import com.xiaofengzi.xfzzone.util.NetworkUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @name: JWTLoginFilter
 * @description: JWT登陆过滤器
 * @author: D.Z
 * @created: 2018-08-22 14:58
 **/
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        // JSON反序列化成 AccountCredentials
        LoginReqDTO creds = new ObjectMapper().readValue(req.getInputStream(), LoginReqDTO.class);

        String ip = NetworkUtil.getIpAddress(req);
        creds.setIp(ip);
        // 返回一个验证令牌
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(creds.getAccountCode(), creds

        ));
    }

    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        TokenAuthenticationService.addAuthentication(res, auth);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        TransResult<Object> tr = new TransResult<Object>();
        tr.setResultCode("0");
        tr.setResultInfoDesc("");
        tr.setObject(failed.getMessage());
        response.getOutputStream().println(GsonUtil.writeValue(tr));

    }
}

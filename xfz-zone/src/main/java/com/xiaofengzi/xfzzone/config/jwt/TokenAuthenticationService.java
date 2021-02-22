package com.xiaofengzi.xfzzone.config.jwt;

import com.xiaofengzi.xfzzone.dto.common.TransResult;
import com.xiaofengzi.xfzzone.dto.common.XIAOFENGZI_ZONE;
import com.xiaofengzi.xfzzone.dto.login.JumpDTO;
import com.xiaofengzi.xfzzone.dto.login.LoginResDTO;
import com.xiaofengzi.xfzzone.util.GsonUtil;
import com.xiaofengzi.xfzzone.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @name: TokenAuthenticationService
 * @description: JWT验签及生成
 * @author: D.Z
 * @created: 2018-08-22 15:11
 **/
public class TokenAuthenticationService {

    // JWT生成方法
    public static void addAuthentication(HttpServletResponse response, Authentication authentication) {

        TransResult<Object> transResult = (TransResult<Object>) authentication.getPrincipal();
        LoginResDTO loginResDTO = (LoginResDTO) transResult.getObject();
        String agentCode = loginResDTO.getAccountCode();
        // 生成JWT
        String JWT = Jwts.builder()
                // // 保存权限（角色）
                // .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                // 用户名写入标题
                .setSubject(agentCode)
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + XIAOFENGZI_ZONE.JWT_EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, XIAOFENGZI_ZONE.JWT_SECRET).compact();
        loginResDTO.setAccountCodeSM(JWT);
        transResult.setResultInfo("");
        transResult.setObject(loginResDTO);
        // 将 代理人实体类 写入 body
        try {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().write(GsonUtil.writeValue(transResult).getBytes("utf-8"));
                        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // JWT验证方法
    public static Authentication getAuthentication(HttpServletRequest request) {

        // 从Header中拿到token
        String token = request.getHeader(XIAOFENGZI_ZONE.JWT_HEADER_STRING);

        String accountCode = request.getHeader(XIAOFENGZI_ZONE.ACCOUNT_CODE);

        if (StringUtils.isNotEmpty(token) && !"undefined".equals(token)) {
            Claims claims = null;
            try {
                
                // 解析 Token
                claims = Jwts.parser()
                        // 验签
                        .setSigningKey(XIAOFENGZI_ZONE.JWT_SECRET)
                        // 去掉 Bearer
                        .parseClaimsJws(token.replace(XIAOFENGZI_ZONE.JWT_TOKEN_PREFIX, "")).getBody();
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
            // 拿到工号
            String agentCode = claims.getSubject();
            request.setAttribute(XIAOFENGZI_ZONE.ACCOUNT_CODE, agentCode);

            @SuppressWarnings("unchecked")
            List<JumpDTO> jumpDTOs = (List<JumpDTO>) claims.get(XIAOFENGZI_ZONE.JUMP);
            
            if(StringUtil.isNotEmpty(accountCode)) {
                request.setAttribute(XIAOFENGZI_ZONE.ACCOUNT_CODE, accountCode);
                request.setAttribute(XIAOFENGZI_ZONE.JUMP, jumpDTOs);
            }
            // 返回验证令牌
            return agentCode != null ? new UsernamePasswordAuthenticationToken(agentCode, null, null) : null;
        }
        return null;
    }
}
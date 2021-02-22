package com.xiaofengzi.wxapi.config.jwt;

import com.xiaofengzi.wxapi.platform.filter.JWTAuthenticationFilter;
import com.xiaofengzi.wxapi.platform.filter.JWTLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @name: WebSecurityConfig
 * @description: 安全设置类
 * @author: D.Z
 * @created: 2018-08-22 14:44
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 设置 HTTP 验证规则
     *
     * @param http
     * @throws Exception
     */
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/xfz/graphValidateCode/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/login"
                       )
                .permitAll()
                .anyRequest().authenticated().and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/api/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 注入beean
     *
     * @return
     */
    @Bean
    CustomAuthenticationProvider customProvider() {
        return new CustomAuthenticationProvider();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(customProvider());
    }

}

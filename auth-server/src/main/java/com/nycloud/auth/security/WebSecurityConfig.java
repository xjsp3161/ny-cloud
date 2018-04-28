package com.nycloud.auth.security;

import com.nycloud.auth.security.filter.AuthenticationTokenFilter;
import com.nycloud.auth.security.handler.AccessHandler;
import com.nycloud.auth.security.handler.AuthenticationHandler;
import com.nycloud.auth.security.properties.PermitAllUrlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author super
 * @version 0.0.1
 * @descration spring安全初始化配置
 */
@Configuration      // 声明为配置类
@EnableWebSecurity  // 启用 Spring Security web 安全的功能
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 注册 401 处理器
     */
    @Autowired
    private AuthenticationHandler authenticationHandler;

    /**
     * 注册 403 处理器
     */
    @Autowired
    private AccessHandler accessHandler;

    @Autowired
    private PermitAllUrlProperties permitAllUrlProperties;

    /**
     * 注册 token 转换拦截器为 bean
     * 如果客户端传来了 token ，那么通过拦截器解析 token 赋予用户权限
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**").authenticated()
                .antMatchers(permitAllUrlProperties.getPermitallPatterns()).permitAll()
                .and()
                .exceptionHandling()

                .and()
                .csrf()
                /* 禁用 Spring Security 自带的跨域处理 */
                .disable()
                /* 定制我们自己的 session 策略 */
                .sessionManagement()
                /* 调整为让 Spring Security 不创建和使用 session */
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        /*
         * 本次 json web token 权限控制的核心配置部分
         * 在 Spring Security 开始判断本次会话是否有权限时的前一瞬间
         * 通过添加过滤器将 token 解析，将用户所有的权限写入本次会话
         */
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}

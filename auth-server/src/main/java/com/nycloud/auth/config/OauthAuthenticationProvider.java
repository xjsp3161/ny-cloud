package com.nycloud.auth.config;

import java.util.Collection;
import java.util.HashSet;

import com.nycloud.auth.model.SysUser;
import com.nycloud.auth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/4 0004
 * @modified By:
 * @version: 1.0
 **/
@Component
public class OauthAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SysUser sysUser = sysUserService.findByUserName(authentication.getName());
        System.out.println(authentication.getName() + "+++++++++++++++++" + authentication.getCredentials());
        if (sysUser == null || !sysUser.getPassword().equals(authentication.getCredentials())) {
            throw new BadCredentialsException("用户名或密码错误。");
        }
        Collection<? extends GrantedAuthority> authorities = new HashSet<>();

        User user = new User(sysUser.getUsername(), sysUser.getPassword(), new HashSet<>());
        return new UsernamePasswordAuthenticationToken(user, sysUser.getPassword(),
                authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
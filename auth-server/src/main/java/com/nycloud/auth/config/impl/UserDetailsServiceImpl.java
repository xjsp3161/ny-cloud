package com.nycloud.auth.config.impl;

import com.nycloud.auth.config.JdbcUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;


/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/25 0025
 * @modified By:
 * @version: 1.0
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    JdbcUserDetailsService jdbcUserDetailsService;

    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = jdbcUserDetailsService.loadClientByUserName(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        return new User(userDetails.getUsername(), userDetails.getPassword(), new HashSet<>());
    }
}

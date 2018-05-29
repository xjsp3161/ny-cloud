package com.nycloud.auth.config.service;

import com.nycloud.auth.config.custom.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/25 0025
 * @modified By:
 * @version: 1.0
 **/
@Repository
public class JdbcUserDetailsService {

    private String selectUserDetailsSql;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcUserDetailsService() {
        this.selectUserDetailsSql = "select id, username, `password`, `enable`, authorities, `name` from sys_user where username = ?";
    }

    public CustomUserDetails loadClientByUserName(String username) throws InvalidClientException {
        try {
            CustomUserDetails details = this.jdbcTemplate.queryForObject(this.selectUserDetailsSql, new JdbcUserDetailsService.UserDetailsRowMapper(), new Object[]{username});
            return details;
        } catch (EmptyResultDataAccessException var4) {
            throw new NoSuchClientException("No client with requested username: " + username);
        }
    }

    private static class UserDetailsRowMapper implements RowMapper<CustomUserDetails> {

        @Override
        public CustomUserDetails mapRow(ResultSet resultSet, int i) throws SQLException {
            CustomUserDetails customUserDetails = new CustomUserDetails();
            customUserDetails.setUserId(resultSet.getString(1));
            customUserDetails.setUsername(resultSet.getString(2));
            customUserDetails.setPassword(resultSet.getString(3));
            customUserDetails.setEnabled(resultSet.getBoolean(4));
            customUserDetails.setAuthorities(new HashSet<>());
            return customUserDetails;
        }

    }

}

package com.nycloud.auth.config;

import com.nycloud.auth.config.custom.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

@Repository
public class JdbcUserDetailsService {

    private String selectUserDetailsSql;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcUserDetailsService() {
        this.selectUserDetailsSql = "select id, username, `password`, `enable`, authorities, `name` from sys_user where username = ?";
    }

    public UserDetails loadClientByUserName(String username) throws InvalidClientException {
        try {
            UserDetails details = this.jdbcTemplate.queryForObject(this.selectUserDetailsSql, new JdbcUserDetailsService.UserDetailsRowMapper(), new Object[]{username});
            return details;
        } catch (EmptyResultDataAccessException var4) {
            throw new NoSuchClientException("No client with requested username: " + username);
        }
    }

    private static class UserDetailsRowMapper implements RowMapper<UserDetails> {

        @Override
        public UserDetails mapRow(ResultSet resultSet, int i) throws SQLException {
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

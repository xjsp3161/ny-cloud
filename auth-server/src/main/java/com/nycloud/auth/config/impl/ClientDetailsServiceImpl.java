package com.nycloud.auth.config.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/5/8 0008
 * @modified By:
 * @version: 1.0
 **/
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    DataSource dataSource;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException{
        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        ClientDetails clientDetails = jdbcClientDetailsService.loadClientByClientId(clientId);
        if(clientDetails == null) {
            throw new ClientRegistrationException("应用" + clientId + "不存在!");
        }
        return clientDetails;
    }
}

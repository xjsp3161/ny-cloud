package com.nycloud.admin.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/6/9 0009
 * @modified By:
 * @version: 1.0
 **/
@Component
public class AuthenticationConverter implements Converter<String, Authentication> {

    @Override
    public Authentication convert(String s) {
        if (!StringUtils.isBlank(s)) {

        }
        return null;
    }
}

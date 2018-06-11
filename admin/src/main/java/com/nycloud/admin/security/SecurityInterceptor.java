/**
 * Copyright(c) Cloudolp Technology Co.,Ltd.
 * All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of Cloudolp
 * Technology Co.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Cloudolp Technology Co.,Ltd.
 * For more information about Cloudolp, welcome to http://www.cloudolp.com
 * <p>
 * project: peony-spring
 * <p>
 * Revision History:
 * Date		    Version		Name				Description
 * 3/3/2017	1.0			Franklin			Creation File
 */
package com.nycloud.admin.security;

import com.nycloud.admin.model.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 *
 *
 * @author Franklin
 * @date 3/3/2017 10:52 AM
 */

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (logger.isDebugEnabled()) {
            logger.debug("current request url: {}" , requestURI);
        }
        String userId = request.getHeader("userId");
        String userName = request.getHeader("username");
        try{
            Long uid = Long.valueOf(userId);
            UserEntity userEntity = new UserEntity(uid, userName);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userEntity, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Object p = authentication.getPrincipal();
            if (logger.isDebugEnabled()) {
                logger.debug("Receive a request from ,authentication: {}", authentication);
                logger.debug("SecurityInterceptor principal = {}", p);
            }
            return super.preHandle(request, response, handler);
        }catch(NumberFormatException e){
            logger.debug(e.getLocalizedMessage());
            response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            return false;

        }
    }

}
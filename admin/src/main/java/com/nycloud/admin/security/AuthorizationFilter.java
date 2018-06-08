package com.nycloud.admin.security;

import com.nycloud.admin.model.SysResource;
import com.nycloud.admin.model.SysUser;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.constants.AccessType;
import com.nycloud.security.constants.SecurityConstants;
import com.nycloud.security.filter.UserContext;
import com.nycloud.security.security.CustomAuthentication;
import com.nycloud.security.security.SimpleGrantedAuthority;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author keets
 */
public class AuthorizationFilter implements Filter {

    @Autowired
    private FeignAuthClient feignAuthClient;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器正在执行...");
        String userId = ((HttpServletRequest) servletRequest).getHeader(SecurityConstants.USER_ID_IN_HEADER);
        if (StringUtils.isBlank(userId)) {

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

package com.nycloud.admin.filter;

import com.nycloud.admin.client.FeignAuthClient;
import com.nycloud.admin.constants.AccessType;
import com.nycloud.admin.constants.SecurityConstants;
import com.nycloud.admin.model.SysResource;
import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.security.CustomAuthentication;
import com.nycloud.admin.security.SimpleGrantedAuthority;
import com.nycloud.common.vo.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        // pass the request along the filter chain
        String userId = ((HttpServletRequest) servletRequest).getHeader(SecurityConstants.USER_ID_IN_HEADER);

        if (StringUtils.isNotEmpty(userId)) {
            UserContext userContext = new UserContext(userId);
            userContext.setAccessType(AccessType.ACCESS_TYPE_NORMAL);

            HttpResponse<SysUser> response = feignAuthClient.getUserResources(Long.valueOf(userId));
            List<SysResource> permissionList = response.getData().getResourceList();
            List<SimpleGrantedAuthority> authorityList = new ArrayList();
            for (SysResource sysResource : permissionList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority();
                authority.setAuthority(sysResource.getUrl());
                authorityList.add(authority);
            }

            CustomAuthentication userAuth  = new CustomAuthentication();
            userAuth.setAuthorities(authorityList);
            userContext.setAuthorities(authorityList);
            userContext.setAuthentication(userAuth);
            SecurityContextHolder.setContext(userContext);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

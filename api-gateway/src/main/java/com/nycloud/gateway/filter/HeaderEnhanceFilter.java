package com.nycloud.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.context.RequestContext;
import com.nycloud.gateway.constants.SecurityConstants;
import com.nycloud.gateway.properties.PermitAllUrlProperties;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/4/26 0026
 * @modified By:
 * @version: 1.0
 **/
public class HeaderEnhanceFilter implements Filter{

    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderEnhanceFilter.class);

    private static final String ANONYMOUS_USER_ID = "d4a65d04-a5a3-465c-8408-405971ac3346";

    @Autowired
    private PermitAllUrlProperties permitAllUrlProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String authorization = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        // test if request url is permit all , then remove authorization from header
        LOGGER.info(String.format("Enhance request URI : %s.", requestURI));
        System.out.println("requestURI = " + requestURI);
        // 判断是否是公共资源URL 和  登录URL访问，这两个是不需要验证头部的，直接转发到对应的服务
        if(isPermitAllUrl(requestURI) && isNotOAuthEndpoint(requestURI)) {
            HttpServletRequest resetRequest = removeValueFromRequestHeader((HttpServletRequest) servletRequest);
            filterChain.doFilter(resetRequest, servletResponse);
            return;
        }
        if (StringUtils.isNotEmpty(authorization)) {
            // 判断是否是JWT Token
            if (isJwtBearerToken(authorization)) {
                try {
                    authorization = StringUtils.substringBetween(authorization, ".");
                    String decoded = new String(Base64.decodeBase64(authorization));

                    Map properties = new ObjectMapper().readValue(decoded, Map.class);

                    String userId = (String) properties.get(SecurityConstants.USER_ID_IN_HEADER);

                    RequestContext.getCurrentContext().addZuulRequestHeader(SecurityConstants.USER_ID_IN_HEADER, userId);
                } catch (Exception e) {
                    LOGGER.error("Failed to customize header for the request, but still release it as the it would be regarded without any user details.", e);
                }
            }
        } else {
            LOGGER.info("Regard this request as anonymous request, so set anonymous user_id in the header.");
            RequestContext.getCurrentContext().addZuulRequestHeader(SecurityConstants.USER_ID_IN_HEADER, ANONYMOUS_USER_ID);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * 根据传入的token返回 token的类型
     * @param token
     * @return
     */
    private boolean isJwtBearerToken(String token) {
        return StringUtils.countMatches(token, ".") == 2 && (token.startsWith("Bearer") || token.startsWith("bearer"));
    }

    private boolean isNotOAuthEndpoint(String requestURI) {
        return !requestURI.contains("/login");
    }

    private HttpServletRequestWrapper removeValueFromRequestHeader(HttpServletRequest request) {
        HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(request) {
            private Set<String> headerNameSet;
            private Set<String> headerSet;

            @Override
            public Enumeration<String> getHeaderNames() {
                if (headerNameSet == null) {
                    // first time this method is called, cache the wrapped request's header names:
                    headerNameSet = new HashSet();
                    Enumeration<String> wrappedHeaderNames = super.getHeaderNames();
                    while (wrappedHeaderNames.hasMoreElements()) {
                        String headerName = wrappedHeaderNames.nextElement();
                        if (!"Authorization".equalsIgnoreCase(headerName)) {
                            headerNameSet.add(headerName);
                        }
                    }
                    //set default header name value of tenant id and user id
                    headerNameSet.add(SecurityConstants.USER_ID_IN_HEADER);
                }

                return Collections.enumeration(headerNameSet);
            }

            @Override
            public Enumeration<String> getHeaders(String name) {

                if ("Authorization".equalsIgnoreCase(name)) {
                    return Collections.emptyEnumeration();
                }
                if (SecurityConstants.USER_ID_IN_HEADER.equalsIgnoreCase(name)) {
                    headerSet = new HashSet();
                    headerSet.add(ANONYMOUS_USER_ID);
                    return Collections.enumeration(headerSet);
                }
                return super.getHeaders(name);
            }

            @Override
            public String getHeader(String name) {
                if ("Authorization".equalsIgnoreCase(name)) {
                    return null;
                }
                if (SecurityConstants.USER_ID_IN_HEADER.equalsIgnoreCase(name)) {
                    return ANONYMOUS_USER_ID;
                }
                return super.getHeader(name);
            }
        };
        return httpServletRequestWrapper;
    }

    private boolean isPermitAllUrl(String url) {
        return permitAllUrlProperties.isPermitAllUrl(url);
    }

}
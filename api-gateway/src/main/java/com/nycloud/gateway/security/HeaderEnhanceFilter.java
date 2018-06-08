package com.nycloud.gateway.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.nycloud.gateway.constants.SecurityConstants;
import com.nycloud.gateway.properties.PermitAllUrlProperties;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HeaderEnhanceFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderEnhanceFilter.class);

    private static final String ANONYMOUS_USER_ID = "d4a65d04-a5a3-465c-8408-405971ac3346";

    @Autowired
    private PermitAllUrlProperties permitAllUrlProperties;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    class User {

        public User(String userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        public String userId;
        public String username;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String authorization = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        // test if request url is permit all , then remove authorization from header
        LOGGER.info(String.format("Enhance request URI : %s.", requestURI));
        if(isPermitAllUrl(requestURI) && isNotOAuthEndpoint(requestURI)) {
            HttpServletRequest resetRequest = removeValueFromRequestHeader((HttpServletRequest) servletRequest);
            filterChain.doFilter(resetRequest, servletResponse);
            return;
        } else {
            if (StringUtils.isNotEmpty(authorization)) {
                // 判断是否是jwt token
                if (isJwtBearerToken(authorization)) {
                    try {
                        authorization = StringUtils.substringBetween(authorization, ".");
                        String decoded = new String(Base64.decodeBase64(authorization));
                        Map properties = new ObjectMapper().readValue(decoded, Map.class);
                        
                        String userId = (String) properties.get(SecurityConstants.USER_ID_IN_HEADER);
                        RequestContext.getCurrentContext().addZuulRequestHeader(SecurityConstants.USER_ID_IN_HEADER, userId);

                        RequestContext ctx = RequestContext.getCurrentContext();
                        HttpServletRequest request = ctx.getRequest();
                        InputStream in = (InputStream) ctx.get("requestEntity");
                        String body = getInputString(in);

                        ObjectMapper objectMapper = new ObjectMapper();
                        Map<String, Object> map = objectMapper.readValue(body, Map.class);

                        String username = (String) properties.get("username");
                        Authentication authentication = new UsernamePasswordAuthenticationToken(new User(userId, username), null);
                        map.put("authentication", authentication);

                        StringWriter sw = new StringWriter();
                        objectMapper.writeValue(sw, map);
                        final byte[] reqBodyBytes = sw.toString().getBytes();
                        RequestContext.getCurrentContext().setRequest(new HttpServletRequestWrapper(request) {
                            @Override
                            public ServletInputStream getInputStream() throws IOException {
                                return new ServletInputStreamWrapper(reqBodyBytes);
                            }

                            @Override
                            public int getContentLength() {
                                return reqBodyBytes.length;
                            }

                            @Override
                            public long getContentLengthLong() {
                                return reqBodyBytes.length;
                            }
                        });
                    } catch (Exception e) {
                        LOGGER.error("Failed to customize header for the request, but still release it as the it would be regarded without any user details.", e);
                    }
                }
            } else {
                LOGGER.info("Regard this request as anonymous request, so set anonymous user_id in the header.");
                RequestContext.getCurrentContext().addZuulRequestHeader(SecurityConstants.USER_ID_IN_HEADER, ANONYMOUS_USER_ID);
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public String getInputString(InputStream in) {
        try {
            BufferedReader bf=new BufferedReader(new InputStreamReader(in,"UTF-8"));
            //最好在将字节流转换为字符流的时候 进行转码
            StringBuffer buffer=new StringBuffer();
            String line="";
            while((line=bf.readLine())!=null){
                buffer.append(line);
            }

            return buffer.toString();
        } catch (Exception e) {

        }
       return null;
    }

    @Override
    public void destroy() {

    }

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

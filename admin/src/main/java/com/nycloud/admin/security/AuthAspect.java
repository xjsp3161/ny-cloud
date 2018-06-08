package com.nycloud.admin.security;

import com.netflix.ribbon.proxy.annotation.Http;
import com.nycloud.admin.model.SysResource;
import com.nycloud.admin.model.SysUser;
import com.nycloud.admin.service.SysUserService;
import com.nycloud.common.constants.HttpConstant;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.constants.AccessType;
import com.nycloud.security.filter.UserContext;
import com.nycloud.security.security.CustomAuthentication;
import com.nycloud.security.security.CustomerSecurityExpressionRoot;
import com.nycloud.security.security.SimpleGrantedAuthority;
import com.spotify.docker.client.shaded.javax.ws.rs.ForbiddenException;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by keets on 2017/12/7.
 */
@Component
@Aspect
public class AuthAspect {

	
//	@Autowired
//    private FeignAuthClient feignAuthClient;

	@Autowired
	private SysUserService sysUserService;
	
    @Pointcut("@annotation(com.nycloud.security.annotation.PreAuth)")
    private void cut() {
    }

    /**
     * 定制一个环绕通知
     * 当想获得注解里面的属性，可以直接注入改注解
     *
     * @param joinPoint
     * @param preAuth
     */
    @Around("cut()&&@annotation(preAuth)")
    public Object record(ProceedingJoinPoint joinPoint, PreAuth preAuth) throws Throwable {
    	
    	 String userId = "196618686130565120";
    	 UserContext userContext = new UserContext(userId);
         userContext.setAccessType(AccessType.ACCESS_TYPE_NORMAL);

//         HttpResponse<SysUser> response = feignAuthClient.getUserResources(Long.valueOf(userId));
         List<SysResource> permissionList = sysUserService.selectUserResources(Long.valueOf(userId)).getResourceList();
         List<SimpleGrantedAuthority> authorityList = new ArrayList();
         for (SysResource sysResource : permissionList) {
             SimpleGrantedAuthority authority = new SimpleGrantedAuthority();
             authority.setAuthority(sysResource.getCode());
             authorityList.add(authority);
         }

         CustomAuthentication userAuth  = new CustomAuthentication();
         userAuth.setAuthorities(authorityList);
         userContext.setAuthorities(authorityList);
         userContext.setAuthentication(userAuth);
         SecurityContextHolder.setContext(userContext);
         
    	

        String value = preAuth.value();

        SecurityContextHolder.getContext();
        //Spring EL 对value进行解析
        SecurityExpressionOperations operations = new CustomerSecurityExpressionRoot(SecurityContextHolder.getContext().getAuthentication());
        StandardEvaluationContext operationContext = new StandardEvaluationContext(operations);
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(value);
        boolean result = expression.getValue(operationContext, boolean.class);
        if (result) {
            return joinPoint.proceed();
        }
        return HttpResponse.resultError(HttpConstant.ERROR_403, HttpConstant.ERROR_403_TEXT);
    }


}


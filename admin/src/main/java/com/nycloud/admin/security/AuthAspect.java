package com.nycloud.admin.security;

import com.nycloud.admin.model.SysResource;
import com.nycloud.admin.service.SysUserService;
import com.nycloud.admin.vo.SysUserDetail;
import com.nycloud.common.constants.HttpConstant;
import com.nycloud.common.constants.SysConstant;
import com.nycloud.common.utils.ListUtils;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.constants.AccessType;
import com.nycloud.security.filter.UserContext;
import com.nycloud.security.security.CustomAuthentication;
import com.nycloud.security.security.CustomerSecurityExpressionRoot;
import com.nycloud.security.security.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
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
import org.springframework.security.core.Authentication;
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
        // 获取Authentication中存储的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity =  (UserEntity) authentication.getPrincipal();
        // 获取用户的详细信息包括（用户所在组，角色，权限，资源）
        SysUserDetail userDetail = sysUserService.selectUserDetail(userEntity.getUserId());
        // 判断用户是否分配角色，没有分配直接返回无权限访问
        if (ListUtils.isEmpty(userDetail.getRoleCodes())) {
            return HttpResponse.resultError(HttpConstant.ERROR_403, HttpConstant.ERROR_403_TEXT);
        }
        // 判断用户角色是否是超级管理员，是的话直接跳过资源判断，执行Controller方法
        if (userDetail.getRoleCodes().contains(SysConstant.SUPER_ADMIN_ROLE_CODE)) {
            return joinPoint.proceed();
        }
        // 将资源添加到到SimpleGrantedAuthority中
        List<SysResource> permissionList = userDetail.getResourceList();
        List<SimpleGrantedAuthority> authorityList = new ArrayList();
        for (SysResource sysResource : permissionList) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority();
            authority.setAuthority(sysResource.getCode());
            authorityList.add(authority);
        }
        // 构建UserContext 对象
        UserContext userContext = new UserContext(String.valueOf(userEntity.getUserId()));
        userContext.setAccessType(AccessType.ACCESS_TYPE_NORMAL);
        CustomAuthentication userAuth  = new CustomAuthentication();
        userAuth.setAuthorities(authorityList);
        userContext.setAuthorities(authorityList);
        userContext.setAuthentication(userAuth);

        //Spring EL 对value进行解析
        SecurityExpressionOperations operations = new CustomerSecurityExpressionRoot(userContext.getAuthentication());
        StandardEvaluationContext operationContext = new StandardEvaluationContext(operations);
        ExpressionParser parser = new SpelExpressionParser();
        String value = preAuth.value();
        Expression expression = parser.parseExpression(value);
        boolean result = expression.getValue(operationContext, boolean.class);
        if (result) {
            return joinPoint.proceed();
        }
        return HttpResponse.resultError(HttpConstant.ERROR_403, HttpConstant.ERROR_403_TEXT);
    }


}


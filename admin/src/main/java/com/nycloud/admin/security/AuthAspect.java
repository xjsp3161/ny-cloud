package com.nycloud.admin.security;

import com.netflix.ribbon.proxy.annotation.Http;
import com.nycloud.common.constants.HttpConstant;
import com.nycloud.common.vo.HttpResponse;
import com.nycloud.security.annotation.PreAuth;
import com.nycloud.security.security.CustomerSecurityExpressionRoot;
import com.spotify.docker.client.shaded.javax.ws.rs.ForbiddenException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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


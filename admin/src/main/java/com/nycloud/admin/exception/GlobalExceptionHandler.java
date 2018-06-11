package com.nycloud.admin.exception;

import com.google.api.client.repackaged.com.google.common.base.Throwables;
import com.nycloud.common.vo.HttpResponse;
import com.sun.jersey.api.MessageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/6/4 0004
 * @modified By:
 * @version: 1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    protected static final String DEFAULT_ERROR_MESSAGE = "系统忙，请稍后再试";

    @ExceptionHandler(NotFoundParentNodeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpResponse handle404Error(HttpServletRequest request, HttpServletResponse response) {
        return HttpResponse.resultError(404, "找不到您要访问的api");
    }

    @ExceptionHandler(Exception.class)
    public HttpResponse handle500Error(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        return this.handleError(request, response, e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected HttpResponse handleError(HttpServletRequest req, HttpServletResponse rsp, Exception e, HttpStatus status) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        String errorMsg = e instanceof MessageException ? e.getMessage() : DEFAULT_ERROR_MESSAGE;
        String errorStack = Throwables.getStackTraceAsString(e);

        getLogger().error("Request: {} raised {}", req.getRequestURI(), errorStack);
        String error = new StringBuffer().append(req.getRequestURL().toString()).append(errorStack).append(errorMsg).toString();
        return HttpResponse.resultError(500, error);
    }

    public Logger getLogger() {
        return LoggerFactory.getLogger(GlobalExceptionHandler.class);
    }

}

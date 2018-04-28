package com.nycloud.auth.security.handler;

import com.nycloud.auth.util.HttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: super.wu
 * @date: Created in 2018/4/28 0028
 * @modified By:
 * @version: 1.0
 **/
@Component
public class AccessHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //返回json形式的错误信息
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().println(new HttpResponse<>().error(403, "Forbidden").toJsonString());
        httpServletResponse.getWriter().flush();
    }
}

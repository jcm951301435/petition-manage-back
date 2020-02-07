package com.ssy.petition.config.handler;

import com.ssy.petition.common.CommonResult;
import com.ssy.petition.util.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 未登录
 *
 * @author jcm
 */
@Component
public class ResultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(JsonUtils.objectToJson(CommonResult.unauthorized(exception.getMessage())));
        out.flush();
    }

}

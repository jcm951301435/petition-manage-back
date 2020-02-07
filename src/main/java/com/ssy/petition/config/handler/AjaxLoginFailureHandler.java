package com.ssy.petition.config.handler;

import com.ssy.petition.common.CommonResult;
import com.ssy.petition.util.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败
 * 不使用默认登录方式、此处废弃
 *
 * @author jcm
 */
@Component
@Deprecated
public class AjaxLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(JsonUtils.objectToJson(CommonResult.unauthorized(exception.getMessage())));
        out.flush();
    }

}

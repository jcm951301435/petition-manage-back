package com.ssy.petition.config.handler;

import com.ssy.petition.common.CommonResult;
import com.ssy.petition.util.JsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录成功
 * 不使用默认登录方式、此处废弃
 *
 * @author jcm
 */
@Component
@Deprecated
public class AjaxLoginSuccessHandler implements AuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(JsonUtils.objectToJson(CommonResult.success("登录成功")));
        out.flush();
    }
}

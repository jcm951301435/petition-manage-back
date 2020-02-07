package com.ssy.petition.config.handler;

import com.ssy.petition.common.CommonResult;
import com.ssy.petition.util.JsonUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 无权访问
 *
 * @author jcm
 */
@Component
public class ResultAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(JsonUtils.objectToJson(CommonResult.forbidden(exception.getMessage())));
        out.flush();
    }

}
